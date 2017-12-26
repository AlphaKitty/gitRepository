package com.dhcc.zhyl.springMVC.dao;

import java.util.List;

import com.dhcc.zhyl.springMVC.entity.AcctUser;
import com.dhcc.zhyl.springMVC.entity.Response;

public interface UserDao {

	List<AcctUser> showAll();

	Response addUser(AcctUser acctUser);

	Response delUser(AcctUser acctUser);

	Response altUser(AcctUser acctUser);

	List<AcctUser> findUser(AcctUser acctUser);

}
