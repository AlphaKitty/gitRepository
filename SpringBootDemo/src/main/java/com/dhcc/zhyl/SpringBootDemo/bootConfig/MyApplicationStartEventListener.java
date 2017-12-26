package com.dhcc.zhyl.SpringBootDemo.bootConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationStartEventListener implements ApplicationListener<ApplicationStartedEvent> {

	private Logger logger = LoggerFactory.getLogger(MyApplicationStartEventListener.class);

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		SpringApplication app = event.getSpringApplication();
		app.setShowBanner(true);// 是否显示Spring标志信息
		app.setLogStartupInfo(true);// 是否显示和运行机器有关的信息
		app.setRegisterShutdownHook(true);// bean销毁 默认true
		logger.debug("***************MyApplicationStartEvent****************");
	}

}
