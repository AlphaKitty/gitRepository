package com.dhcc.zhyl.SpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dhcc.zhyl.SpringBootDemo.bootConfig.MyApplicationStartEventListener;

@SpringBootApplication
public class SpringBootDemo {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootDemo.class);
		app.addListeners(new MyApplicationStartEventListener());
		app.run(args);
		// SpringApplication.run(SpringBootDemo.class, args);
	}
}