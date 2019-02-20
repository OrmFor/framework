package com.yinmimoney.web.p2pnew.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.yinmimoney.web.p2pnew.constant.SysLogConstant;
import com.yinmimoney.web.p2pnew.controller.base.BaseController;
import com.yinmimoney.web.p2pnew.enums.EnumApiTokenStatus;
import com.yinmimoney.web.p2pnew.pojo.ApiToken;
import com.yinmimoney.web.p2pnew.pojo.User;
import com.yinmimoney.web.p2pnew.service.IApiToken;
import com.yinmimoney.web.p2pnew.service.IUser;

import cc.s2m.util.Page;
import cc.s2m.web.utils.webUtils.vo.Expressions;

@Controller("admin_UserController")
@RequestMapping("/admin/user")
public class UserController extends BaseController {
	private static final String MODULE_NAME = "用户列表";

	@Autowired
	private IUser userService;
	@Autowired
	private IApiToken apiTokenService;

	@RequestMapping(value = "/list")
	public String list(Model model, User bean, Integer page, String registerTimeBegin, String registerTimeEnd, String updateTimeBegin, String updateTimeEnd) {
		model.addAttribute("bean", bean);
		model.addAttribute("registerTimeBegin", registerTimeBegin);
		model.addAttribute("registerTimeEnd", registerTimeEnd);
		model.addAttribute("updateTimeBegin", updateTimeBegin);
		model.addAttribute("updateTimeEnd", updateTimeEnd);

		if (page == null)
			page = 1;
		if (bean == null) {
			bean = new User();
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (!Strings.isNullOrEmpty(registerTimeBegin)) {
				bean.and(Expressions.ge("register_time", format.parse(registerTimeBegin)));
			}
			if (!Strings.isNullOrEmpty(registerTimeEnd)) {
				bean.and(Expressions.lt("register_time", DateUtils.addDays(format.parse(registerTimeEnd), 1)));
			}
			if (!Strings.isNullOrEmpty(updateTimeBegin)) {
				bean.and(Expressions.ge("update_time", format.parse(updateTimeBegin)));
			}
			if (!Strings.isNullOrEmpty(updateTimeEnd)) {
				bean.and(Expressions.lt("update_time", DateUtils.addDays(format.parse(updateTimeEnd), 1)));
			}
		} catch (Exception e) {
		}

		if (!"1".equals(getRequest().getParameter("excel"))) {// 导出 EXCEL
			bean.setPageNumber(page);
			bean.setPageSize(50);
		} else {
			bean.setPageNumber(1);
			bean.setPageSize(Integer.MAX_VALUE);
		}

		Page<User> pageBean = userService.getPage(bean, getRequest().getParameterMap());
		model.addAttribute("pageBean", pageBean);

		return "admin/user";
	}

	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwd(Integer id, String pwd) {
		if (id == null) {
			return "id为空";
		}
		if (Strings.isNullOrEmpty(pwd)) {
			return "密码为空";
		}
		User bean = userService.selectByPrimaryKey(id);
		if (bean == null) {
			return "用户不存在";
		}
		bean.setPwd(DigestUtils.md5Hex(pwd).toUpperCase());
		userService.updateByPrimaryKeySelective(bean);
		// 查找有效token
		ApiToken condition = new ApiToken();
		condition.setUserCode(bean.getUserCode());
		condition.setStatus(EnumApiTokenStatus.STATUS_NORMAL.getStatus());
		ApiToken apiToken = apiTokenService.getByCondition(condition);
		// 更新token状态为过期
		apiTokenService.updateApiTokenStatus(apiToken, EnumApiTokenStatus.STATUS_DISABLED);
		addSysLog(MODULE_NAME, SysLogConstant.OPRATE_RESET_PWD, JSONObject.toJSONString(bean));
		return "success";
	}

}