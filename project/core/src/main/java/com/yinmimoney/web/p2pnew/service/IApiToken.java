package com.yinmimoney.web.p2pnew.service;

import com.yinmimoney.web.p2pnew.enums.EnumApiTokenStatus;
import com.yinmimoney.web.p2pnew.pojo.ApiToken;
import com.yinmimoney.web.p2pnew.view.LoginInfo;

import cc.s2m.web.utils.webUtils.service.BaseService;

public interface IApiToken extends BaseService<ApiToken, java.lang.Integer> {
	
	/**
	 * 
	 * @Title processApiToken
	 * @Description app登录处理token
	 * @author wzq
	 * @date 2018年6月7日 下午1:44:03
	 * @param ip ip
	 * @param deviceId 设备id
	 * @param deviceName 设备名
	 * @param userName 用户名
	 * @param pwd 密码
	 * @return LoginInfo
	 */
	public LoginInfo processApiToken(String ip, String deviceId, String deviceName, String userName, String pwd);

	/**
	 * 
	 * @Title updateApiTokenStatus
	 * @Description 更新token状态
	 * @author wzq
	 * @date 2018年6月8日 上午11:09:38
	 * @param apiToken
	 * @param enumApiTokenStatus
	 * @return void
	 */
	public void updateApiTokenStatus(ApiToken apiToken, EnumApiTokenStatus enumApiTokenStatus);

}