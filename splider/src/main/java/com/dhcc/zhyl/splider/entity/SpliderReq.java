package com.dhcc.zhyl.splider.entity;

public class SpliderReq {
	private String url;// 要抓取的网址,需要以协议开头
	private String duration;// 抓取持续时长

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
