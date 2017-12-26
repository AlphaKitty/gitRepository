package com.dhcc.zhyl.springMVC.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhcc.zhyl.springMVC.dao.UserDao;
import com.dhcc.zhyl.springMVC.entity.AcctUser;
import com.dhcc.zhyl.springMVC.entity.Response;
import com.dhcc.zhyl.springMVC.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	// private static final Logger log =
	// Logger.getLogger(UserServiceImpl.class);

	@Override
	public List<AcctUser> showAll() {
		return userDao.showAll();
	}

	@Override
	public Response addUser(AcctUser acctUser) {
		return userDao.addUser(acctUser);
	}

	@Override
	public Response delUser(AcctUser acctUser) {
		return userDao.delUser(acctUser);
	}

	@Override
	public Response altUser(AcctUser acctUser) {
		return userDao.altUser(acctUser);
	}

	@Override
	public List<AcctUser> findUser(AcctUser acctUser) {
		return userDao.findUser(acctUser);
	}
}
