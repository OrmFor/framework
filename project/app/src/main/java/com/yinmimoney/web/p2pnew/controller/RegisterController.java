//package com.yinmimoney.web.p2pnew.controller;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.yinmimoney.web.p2pnew.controller.base.BaseController;
//import com.yinmimoney.web.p2pnew.core.ResultCode;
//import com.yinmimoney.web.p2pnew.core.StatusCode;
//import com.yinmimoney.web.p2pnew.service.IUser;
//
//import cc.s2m.util.IpUtil;
//
//@Controller
//@RequestMapping("/api/1.0/register")
//public class RegisterController extends BaseController {
//
//	private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);
//
//	@Autowired
//	private IUser userService;
//
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody
//	public ResultCode register(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "pwd", required = false) String pwd,
//			@RequestParam(value = "inviteCode", required = false) String inviteCode) {
//		LOGGER.info("userName={}，pwd={}，inviteCode={}", userName, pwd, inviteCode);
//		// 注册
//		userService.processRegister(userName, pwd, inviteCode, IpUtil.getIp(getRequest()));
//		return new ResultCode(StatusCode.SUCCESS);
//	}
//
//}
