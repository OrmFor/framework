//package com.yinmimoney.web.p2pnew.controller;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yinmimoney.web.p2pnew.controller.base.BaseController;
//import com.yinmimoney.web.p2pnew.core.ResultCode;
//import com.yinmimoney.web.p2pnew.core.StatusCode;
//import com.yinmimoney.web.p2pnew.enums.EnumApiTokenStatus;
//import com.yinmimoney.web.p2pnew.exception.BussinessException;
//import com.yinmimoney.web.p2pnew.pojo.ApiToken;
//import com.yinmimoney.web.p2pnew.service.IApiToken;
//import com.yinmimoney.web.p2pnew.util.DateUtil;
//import com.yinmimoney.web.p2pnew.view.LoginInfo;
//
//import cc.s2m.util.IDGenerator;
//
//@Controller
//@RequestMapping("/api/1.0")
//public class LoginController extends BaseController {
//
//	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
//
//	@Autowired
//	private IApiToken apiTokenService;
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultCode login(@RequestParam(value = "deviceId", required = false) String deviceId, @RequestParam(value = "deviceName", required = false) String deviceName,
//			@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "pwd", required = false) String pwd) {
//		LOGGER.info("deviceId={}，deviceName={}，userName={}，pwd={}", deviceId, deviceName, userName, pwd);
//		// app登录处理
//		LoginInfo loginInfo = apiTokenService.processApiToken(getIp(), deviceId, deviceName, userName, pwd);
//		return new ResultCode(StatusCode.SUCCESS, loginInfo);
//	}
//
//	@RequestMapping(value = "/logout", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultCode logout() {
//		ApiToken apiToken = getApiToken();
//		if (apiToken != null) {
//			apiTokenService.updateApiTokenStatus(apiToken, EnumApiTokenStatus.STATUS_DISABLED);
//		}
//		return new ResultCode(StatusCode.SUCCESS);
//	}
//
//	@RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultCode refreshToken() {
//		ApiToken apiToken = getApiToken();
//		String token = IDGenerator.uuid();
//		ApiToken update = new ApiToken();
//		update.setId(apiToken.getId());
//		update.setToken(token);
//		update.setDateUpdate(DateUtil.getNow());
//		apiTokenService.updateByPrimaryKeySelective(update);
//		// 加密token给前端
//		JSONObject json = new JSONObject();
//		json.put("deviceId", apiToken.getDeviceId());
//		json.put("token", token);
//		String signToken = null;
//		try {
//			signToken = new String(Base64.encodeBase64(json.toJSONString().getBytes()), "UTF-8");
//		} catch (Exception e) {
//			throw new BussinessException(StatusCode.ERROR);
//		}
//		return new ResultCode(StatusCode.SUCCESS, signToken);
//	}
//
//}
