package com.yinmimoney.web.p2pnew.interceptors;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.yinmimoney.web.p2pnew.pojo.Admin;
import com.yinmimoney.web.p2pnew.pojo.AdminActions;
import com.yinmimoney.web.p2pnew.pojo.AdminRoleActions;
import com.yinmimoney.web.p2pnew.pojo.AdminRoles;
import com.yinmimoney.web.p2pnew.service.IAdminActions;
import com.yinmimoney.web.p2pnew.service.IAdminRoleActions;
import com.yinmimoney.web.p2pnew.service.IAdminRoles;

import cc.s2m.util.CookieUtil;
import cc.s2m.web.utils.webUtils.StaticProp;
import cc.s2m.web.utils.webUtils.util.AccountDigestUtils;
import cc.s2m.web.utils.webUtils.vo.Expressions;

public class TokenInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LogManager.getLogger(TokenInterceptor.class);

	private List<String> canNoLoginUrls;// 可以不需要登录的链接，但是登录情况下又需要过token，保存用户信息

	@Autowired
	private IAdminActions adminActionsService;
	@Autowired
	private IAdminRoleActions adminRoleActionsService;
	@Autowired
	private IAdminRoles adminRolesService;

	public List<String> getCanNoLoginUrls() {
		return canNoLoginUrls;
	}

	public void setCanNoLoginUrls(List<String> canNoLoginUrls) {
		this.canNoLoginUrls = canNoLoginUrls;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		if (!Strings.isNullOrEmpty(uri) && uri.endsWith("/")) {
			uri = uri.substring(0, uri.length() - 1);
		}
		logger.info("uri:" + uri);
		String adminLoginUrl = "/login";
		StringBuilder redirectURL = new StringBuilder();
		redirectURL.append(uri);
		if (!Strings.isNullOrEmpty(request.getQueryString())) {
			redirectURL.append("?");
			redirectURL.append(request.getQueryString());
		}
		adminLoginUrl += "?redirectURL=" + java.net.URLEncoder.encode(redirectURL.toString(), "UTF-8");
		// logger.info("adminLoginUrl:" + adminLoginUrl);
		// 如果不用token使用cookie获取登录信息
		CookieUtil cookie = new CookieUtil(request, response);
		String cookieAdminStr = cookie.getCookie(StaticProp.cookieID);
		if (cookieAdminStr == null || cookieAdminStr.trim().isEmpty()) {
			response.sendRedirect(adminLoginUrl);
			return false;
		}
		Admin admin = AccountDigestUtils.unserialize(Admin.class, cookieAdminStr, StaticProp.sysConfig.get("cookie.secret.key"));
		if (admin == null) {
			response.sendRedirect(adminLoginUrl);
			return false;
		}
		// 判断是否过期
		String sessionTimeoutStr = StaticProp.sysConfig.get("session.time.out");
		int sessionTimeout = Integer.parseInt(sessionTimeoutStr);
		Date lastVisitDate = admin.getLoginTime();
		if (lastVisitDate == null) {
			response.sendRedirect(adminLoginUrl);
			return false;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(lastVisitDate);
		c.add(Calendar.MINUTE, sessionTimeout);
		if (c.getTime().before(new Date())) {
			response.sendRedirect(adminLoginUrl);
			return false;
		}
		// 插入菜单
		AdminActions cond = new AdminActions();
		cond.setUrl(uri);
		cond.setSysType(0);
		AdminActions actions = adminActionsService.getByCondition(cond);
		if (actions == null) {
			AdminActions insert = new AdminActions();
			insert.setUrl(uri);
			insert.setSysType(0);
			insert.setName("未分配");
			insert.setLevel(1);
			adminActionsService.insertSelective(insert);
		}
		if (canNoLoginUrls != null && canNoLoginUrls.contains(uri)) {
			putOwnerActions(request, admin, cookieAdminStr, cookie);
			return true;
		}
		// 超级管理员全部放行
		if (admin.getUserName().equals("admin")) {
			putOwnerActions(request, admin, cookieAdminStr, cookie);
			return true;
		}
		// 进行权限判断
		AdminRoleActions roleActions = new AdminRoleActions();
		roleActions.setAid(actions.getId());
		List<AdminRoleActions> roleActionsList = adminRoleActionsService.getList(roleActions);
		if (roleActionsList.isEmpty()) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("您无权进行该操作！");
			return false;
		}
		boolean pass = false;// 是否放行
		for (AdminRoleActions ra : roleActionsList) {
			AdminRoles adminRole = new AdminRoles();
			adminRole.setAid(admin.getId());
			adminRole.setRid(ra.getRid());
			adminRole = adminRolesService.getByCondition(adminRole);
			if (adminRole != null) {
				pass = true;
				break;
			}
		}
		if (!pass) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().println("您无权进行该操作！");
			return false;
		}
		putOwnerActions(request, admin, cookieAdminStr, cookie);
		return true;
	}

	private void putOwnerActions(HttpServletRequest request, Admin admin, String cookieAdminStr, CookieUtil cookie) {
		// 重写cookie
		cookieAdminStr = AccountDigestUtils.serialize(admin, StaticProp.sysConfig.get("cookie.secret.key"), null);
		cookie.setCookie(StaticProp.cookieID, cookieAdminStr, -1, null);
		// 放入request
		request.setAttribute("sessionAdmin", admin);
		AdminActions adminActions = new AdminActions();
		adminActions.setIsMenu(true);
		adminActions.setOrderBy("paixu ASC");
		adminActions.setSysType(0);
		if (admin.getUserName().equals("admin")) {
			request.setAttribute("ownerAdminActions", adminActionsService.getList(adminActions));
			return;
		}
		AdminRoles adminRoles = new AdminRoles();
		adminRoles.setAid(admin.getId());
		List<AdminRoles> adminRolesS = adminRolesService.getList(adminRoles);
		List<Integer> roles = Lists.newArrayList(-1);
		for (AdminRoles ar : adminRolesS) {
			roles.add(ar.getRid());
		}
		AdminRoleActions adminRoleActions = new AdminRoleActions();
		adminRoleActions.and(Expressions.in("rid", roles));
		List<AdminRoleActions> adminRoleActionsS = adminRoleActionsService.getList(adminRoleActions);
		List<Integer> actions = Lists.newArrayList(-1);
		for (AdminRoleActions roleActions : adminRoleActionsS) {
			actions.add(roleActions.getAid());
		}
		adminActions.and(Expressions.in("id", actions));
		request.setAttribute("ownerAdminActions", adminActionsService.getList(adminActions));
	}

}
