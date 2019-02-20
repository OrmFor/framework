package com.yinmimoney.web.p2pnew.pojo.entity;

import cc.s2m.web.utils.webUtils.pojo.BaseModelBean;
import org.springframework.format.annotation.DateTimeFormat;

public class UserEntity extends BaseModelBean {
    private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id; // ID
	private java.lang.String userCode; // 用户编号
	private java.lang.String userName; // 用户名
	private java.lang.String pwd; // 密码
	private java.lang.Integer status; // 用户状态，0：已注册，1：已激活
	private java.lang.String inviteCode; // 邀请码
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date registerTime; // 注册时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date updateTime; // 更新时间
	
    public java.lang.Integer getId() {
        return id;
    }
	public void setId(java.lang.Integer id) {
        this.id = id;
    }
    public java.lang.String getUserCode() {
        return userCode;
    }
	public void setUserCode(java.lang.String userCode) {
        this.userCode = userCode;
    }
    public java.lang.String getUserName() {
        return userName;
    }
	public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }
    public java.lang.String getPwd() {
        return pwd;
    }
	public void setPwd(java.lang.String pwd) {
        this.pwd = pwd;
    }
    public java.lang.Integer getStatus() {
        return status;
    }
	public void setStatus(java.lang.Integer status) {
        this.status = status;
    }
    public java.lang.String getInviteCode() {
        return inviteCode;
    }
	public void setInviteCode(java.lang.String inviteCode) {
        this.inviteCode = inviteCode;
    }
    public java.util.Date getRegisterTime() {
        return registerTime;
    }
	public void setRegisterTime(java.util.Date registerTime) {
        this.registerTime = registerTime;
    }
    public java.util.Date getUpdateTime() {
        return updateTime;
    }
	public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
}