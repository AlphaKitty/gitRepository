package com.dhcc.zyl.maven;

import org.springframework.beans.factory.FactoryBean;

public class RunnableFactoryBean implements FactoryBean<YourBean> {

	public YourBean getObject() throws Exception {
		return new YourBean();
	}

	public Class<?> getObjectType() {
		return YourBean.class;
	}

	public boolean isSingleton() {
		return false;
	}

}
