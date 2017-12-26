package com.dhcc.zhyl.springMVC.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dhcc.zhyl.springMVC.entity.AcctUser;
import com.dhcc.zhyl.springMVC.entity.Response;
import com.dhcc.zhyl.springMVC.service.UserService;

@Controller("userController")
@RequestMapping("/user")
public class UserController implements User {

	private static final Logger log = Logger.getLogger(UserController.class);

	@Reference
	@Autowired
	private UserService userService;

	// 查询所有用户信息
	@Override
	@RequestMapping("/showAll")
	public @ResponseBody List<AcctUser> showAll() {
		return userService.showAll();
	}

	// 新增一个用户
	@Override
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody Response addUser(@RequestBody AcctUser acctUser) {
		try {
			return userService.addUser(acctUser);
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				log.debug("controller层异常: 重复的唯一索引值");
				return new Response("0001", "重复的唯一索引值");
			} else
				return new Response("9999", "未知错误");
		}
	}

	// 删除一个用户
	@Override
	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	public @ResponseBody Response delUser(@RequestBody AcctUser acctUser) {
		return userService.delUser(acctUser);
	}

	// 根据身份证号修改一个用户
	@Override
	@RequestMapping(value = "/altUser", method = RequestMethod.POST)
	public @ResponseBody Response altUser(@RequestBody AcctUser acctUser) {
		return userService.altUser(acctUser);
	}

	// 灵活查询
	@Override
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public @ResponseBody List<AcctUser> findUser(@RequestBody AcctUser acctUser) {
		return userService.findUser(acctUser);
	}
}
