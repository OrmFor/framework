package com.yinmimoney.web.p2pnew.view;

/**
 * 
 * @Description 登录返回信息
 * @author wzq
 * @date 2018年11月27日 下午6:55:29
 */
public class LoginInfo {

	private String userName;// 用户名
	private String token;// token，以后要登录的接口，需要token放在head里面，不需要登录的接口放不放不校验
	private Integer status;// 用户状态，0：已注册，1：已激活

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}