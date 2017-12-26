package com.dhcc.zyl.maven;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.json.JSON;
import com.dhcc.zhyl.springMVC.controller.User;
import com.dhcc.zhyl.springMVC.entity.AcctUser;
import com.dhcc.zhyl.springMVC.entity.Response;

public class Consumer {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-dubbo-consumer.xml" });
		context.start();
		System.out.println("request start");

		// 显示所有用户
		User user = (User) context.getBean("userController");
		List<AcctUser> list = user.showAll();
		String json = JSON.json(list);
		System.out.println(json);

		// 添加一个用户
		AcctUser acctUser = new AcctUser();
		acctUser.setCardId("370786199508146060");
		acctUser.setNickName("ZYL");
		acctUser.setTelephone("15069635487");
		Response res = user.addUser(acctUser);
		System.out.println(JSON.json(res));

		// 模拟线程
		System.in.read();
	}

}