package com.yinmimoney.web.p2pnew.enums;

/**
 * 
 * @Description 通知NID类型枚举类
 * @author wzq
 * @date 2018年6月27日 上午11:50:12
 */
public enum EnumNoticeNid {

	/** 注册验证码 **/
	NID_REGISTER("register", "注册验证码"),
	/** 修改登录密码 **/
	NID_MODIFY_PASSWORD("modify_password", "修改登录密码"),
	/** 找回密码 **/
	NID_RESET_PASSWORD("reset_password", "找回密码"),
	/** 快捷登录 **/
	NID_QUICK_LOGIN("quick_login", "快捷登录");

	private String nid;

	private String name;

	private EnumNoticeNid(String nid, String name) {
		this.nid = nid;
		this.name = name;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
