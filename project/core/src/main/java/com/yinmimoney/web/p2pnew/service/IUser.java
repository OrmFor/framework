package com.yinmimoney.web.p2pnew.service;

import com.yinmimoney.web.p2pnew.pojo.User;
import cc.s2m.web.utils.webUtils.service.BaseService;

public interface IUser extends BaseService<User, java.lang.Integer> {
	
	/**
	 * 
	 * @Title processRegister
	 * @Description 注册
	 * @author wzq
	 * @date 2018年11月27日 下午2:51:54
	 * @param userName 用户名
	 * @param pwd 密码
	 * @param inviteCode 邀请码
	 * @param ip IP
	 * @return void
	 */
	void processRegister(String userName, String pwd, String inviteCode, String ip);
	
	/**
	 * 
	 * @Title selectByUserCode
	 * @Description 根据用户编号查询用户信息
	 * @author wzq
	 * @date 2018年11月27日 下午3:33:48
	 * @param userCode 用户编号
	 * @return
	 * @return User
	 */
	User selectByUserCode(String userCode);
	
	/**
	 * 
	 * @Title selectByInviteCode
	 * @Description 根据用户邀请码编号查询用户信息
	 * @author wzq
	 * @date 2018年11月28日 下午1:12:26
	 * @param inviteCode 邀请码
	 * @return
	 * @return User
	 */
	User selectByInviteCode(String inviteCode);
	
}