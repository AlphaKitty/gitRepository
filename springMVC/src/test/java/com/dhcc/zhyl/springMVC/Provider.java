package com.dhcc.zhyl.springMVC;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.JdkVersion;

public class Provider {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-dubbo-provider.xml" });
		context.start();

		System.out.println(JdkVersion.getJavaVersion());
		// 为保证服务一直开着，利用输入流的阻塞来模拟
		System.in.read();
	}

}
