package com.yinmimoney.web.p2pnew.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.yinmimoney.web.p2pnew.core.StatusCode;
import com.yinmimoney.web.p2pnew.dao.UserMapper;
import com.yinmimoney.web.p2pnew.enums.EnumUserCodeType;
import com.yinmimoney.web.p2pnew.exception.BussinessException;
import com.yinmimoney.web.p2pnew.pojo.User;
import com.yinmimoney.web.p2pnew.service.IUser;
import com.yinmimoney.web.p2pnew.service.IUserInviteCodeMax;
import com.yinmimoney.web.p2pnew.util.RegexUtil;
import com.yinmimoney.web.p2pnew.util.UserCodeGenerator;

import cc.s2m.web.utils.webUtils.service.BaseServiceImpl;

@Service
public class UserImpl extends BaseServiceImpl<User, UserMapper, java.lang.Integer> implements IUser {
    @Autowired
    private UserMapper userMapper;
	@Autowired
	private UserCodeGenerator userCodeGenerator;
	@Autowired
	private IUserInviteCodeMax userInviteCodeMaxService;

    protected UserMapper getDao() {
        return userMapper;
    }

	@Override
	public void processRegister(String userName, String pwd, String inviteCode, String ip) {
		if (Strings.isNullOrEmpty(userName)) {
			throw new BussinessException(StatusCode.ERROR_LACK_PARAM);
		}
		if (!RegexUtil.checkInput("^[0-9a-zA-Z]{1,32}$", userName)) {
			throw new BussinessException(StatusCode.ERROR_ACCOUNT_USER_NAME_FORMAT_ERROR);
		}
		if (Strings.isNullOrEmpty(pwd)) {
			throw new BussinessException(StatusCode.ERROR_LACK_PARAM);
		}
		if (pwd.length() > 32) {
			throw new BussinessException(StatusCode.ERROR_ACCOUNT_PWD_TOO_LONG);
		}
		// 检查用户是否存在
		User condition = new User();
		condition.setUserName(userName);
		User euser = getByCondition(condition);
		if (euser != null) {
			throw new BussinessException(StatusCode.ERROR_ACCOUNT_EXIST);
		}
		String userCode = userCodeGenerator.getUserCode(EnumUserCodeType.TYPE_USER.getType());
		// 插入用户
		User user = new User();
		user.setUserCode(userCode);
		user.setUserName(userName);
		user.setPwd(DigestUtils.md5Hex(pwd).toUpperCase());
		user.setInviteCode(userInviteCodeMaxService.processInviteCodeMetch());
		insertSelective(user);
	}

	@Override
	public User selectByUserCode(String userCode) {
		User condition = new User();
		condition.setUserCode(userCode);
		User user = getByCondition(condition);
		return user;
	}

	@Override
	public User selectByInviteCode(String inviteCode) {
		User condition = new User();
		condition.setInviteCode(inviteCode);
		User user = getByCondition(condition);
		return user;
	}

}