package cn.edu.sdau.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.sdau.forum.service.IUserInfoService;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {

	@Autowired
	private IUserInfoService userinfoService;
}
