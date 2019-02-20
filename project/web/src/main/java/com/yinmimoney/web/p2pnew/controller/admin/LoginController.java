package com.yinmimoney.web.p2pnew.controller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.yinmimoney.web.p2pnew.constant.SysLogConstant;
import com.yinmimoney.web.p2pnew.controller.base.BaseController;
import com.yinmimoney.web.p2pnew.enums.EnumAdminStatus;
import com.yinmimoney.web.p2pnew.enums.EnumUserCodeType;
import com.yinmimoney.web.p2pnew.pojo.Admin;
import com.yinmimoney.web.p2pnew.pojo.SysLog;
import com.yinmimoney.web.p2pnew.service.IAdmin;
import com.yinmimoney.web.p2pnew.service.ISysLog;
import com.yinmimoney.web.p2pnew.util.UserCodeGenerator;

import cc.s2m.util.CookieUtil;
import cc.s2m.web.utils.webUtils.StaticProp;
import cc.s2m.web.utils.webUtils.util.AccountDigestUtils;

@Controller("admin_login")
public class LoginController extends BaseController {

	private static final String MODULE_NAME = "登录/退出";

	@Autowired
	private IAdmin adminService;
	@Autowired
	private ISysLog sysLogService;
	@Autowired
	private UserCodeGenerator userCodeGenerator;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model model, String redirectURL) {
		if (!Strings.isNullOrEmpty(redirectURL)) {
			redirectURL = redirectURL.replace("&amp;", "&");
		}
		model.addAttribute("redirectURL", redirectURL);
		return "admin/login";
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	@ResponseBody
	public String check(HttpServletRequest request, HttpServletResponse response, @RequestParam String userName, @RequestParam String passWord, @RequestParam String picCode) {
		if (Strings.isNullOrEmpty(userName)) {
			return "请输入账号";
		}
		if (Strings.isNullOrEmpty(passWord)) {
			return "请输入密码";
		}
		if (Strings.isNullOrEmpty(picCode)) {
			return "请输入图形验证码";
		}
		// 表没有记录插入默认记录
		Admin superAdmin = adminService.getByCondition(null);
		if (superAdmin == null) {
			Admin insert = new Admin();
			insert.setId(1);
			insert.setUserCode(userCodeGenerator.getUserCode(EnumUserCodeType.TYPE_ADMIN.getType()));
			insert.setUserName("admin");
			insert.setRealName("超级管理员");
			insert.setMobilePhone("13500000000");
			insert.setPwd(AccountDigestUtils.getMd5Pwd(insert.getUserName(), "admin"));
			adminService.insertSelective(insert);
		}
		if (!checkCodeIsEqual(request, picCode)) {
			return "图形验证码错误";
		}
		Admin condition = new Admin();
		condition.setUserName(userName);
		Admin admin = adminService.getByCondition(condition);
		if (admin == null) {
			return "管理员不存在";
		}
		if (!admin.getPwd().equals(AccountDigestUtils.getMd5Pwd(admin.getUserName(), passWord))) {
			return "密码错误";
		}
		if (admin.getStatus().intValue() != EnumAdminStatus.STATUS_NORMAL.getStatus()) {
			return "用户状态异常";
		}
		// 更新登录信息
		Admin update = new Admin();
		update.setId(admin.getId());
		update.setLoginTime(new Date());
		update.setLoginIp(getIp());
		adminService.updateByPrimaryKeySelective(update);
		// 重新查询
		admin = adminService.selectByPrimaryKey(admin.getId());
		// 写入cookie
		String cookieAdminStr = AccountDigestUtils.serialize(admin, StaticProp.sysConfig.get("cookie.secret.key"), null);
		CookieUtil cookie = new CookieUtil(request, response);
		cookie.setCookie(StaticProp.cookieID, cookieAdminStr, -1, null);
		// 登录操作记录
		String uri = getRequest().getRequestURI().trim();
		uri = uri.trim();
		if (uri.endsWith("/") && uri.length() > 1) {
			uri = uri.substring(0, uri.length() - 1);
		}
		SysLog sysLog = new SysLog();
		sysLog.setAdminId(admin.getId());
		sysLog.setIp(getIp());
		sysLog.setModuleType("登录");
		sysLog.setOprateType(SysLogConstant.OPRATE_LOGIN);
		sysLog.setMsg(admin.getUserName());
		sysLog.setName("登录");
		sysLog.setUri(uri);
		sysLogService.insertSelective(sysLog);
		return "success";
	}

	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String out(HttpServletRequest request, HttpServletResponse response) {
		Admin admin = getSessionAdmin();
		CookieUtil cookie = new CookieUtil(request, response);
		cookie.removeCookie(StaticProp.cookieID, null);
		addSysLog("退出", SysLogConstant.OPRATE_LOGIN_OUT, admin.getUserName());
		return "redirect:/login";
	}
}
