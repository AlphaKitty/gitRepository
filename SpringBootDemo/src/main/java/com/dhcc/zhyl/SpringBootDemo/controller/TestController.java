package com.dhcc.zhyl.SpringBootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zhyl")
public class TestController {
	@RequestMapping("/gateway")
	public String sayHello() {
		return "hello123";
	}
}
