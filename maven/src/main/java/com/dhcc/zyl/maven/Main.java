package com.dhcc.zyl.maven;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		System.out.println(context.getBean(MyBean.class));
		System.out.println(context.getBean("bean"));
		System.out.println(context.getBean(YourBean.class));
		System.out.println(context.getBean("createRunnableFactoryBean"));
		context.close();
	}
}
