package com.dhcc.zhyl.splider.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhcc.zhyl.splider.entity.Response;
import com.dhcc.zhyl.splider.entity.SpliderReq;
import com.dhcc.zhyl.splider.entity.UrlInfo;
import com.dhcc.zhyl.splider.service.SpliderService;

@RequestMapping("/splider")
@Controller("spliderController")
public class SpliderController implements Splider {

	@Autowired
	private SpliderService spliderService;

	private static final Logger log = Logger.getLogger(SpliderController.class);

	// 新增单条网址记录
	@Override
	@RequestMapping(value = "/addUrl", method = RequestMethod.POST)
	public @ResponseBody Response addUrl(@RequestBody UrlInfo urlInfo) {
		try {
			return spliderService.addUrl(urlInfo);
		} catch (Exception e) {
			return new Response("0001", "网址重复");
		}
	}

	// 批量新增网址记录
	@Override
	@RequestMapping(value = "/addUrls", method = RequestMethod.POST)
	public @ResponseBody Response addUrls(@RequestBody List<UrlInfo> urlInfos) {
		return spliderService.addUrls(urlInfos);
	}

	// 查询全部记录
	@Override
	@RequestMapping(value = "/getAllUrls", method = RequestMethod.GET)
	public @ResponseBody List<UrlInfo> getAllUrls() {
		return spliderService.getAllUrls();
	}

	// 读取文件 转换成List 批量插入
	@Override
	@RequestMapping(value = "/readTxtSaveData", method = RequestMethod.GET)
	public @ResponseBody Response readTxtSaveData() {
		return spliderService.readTxtSaveData("G:\\Users\\Administrator\\workspace\\workspace\\splider\\src\\myselfUrls.txt");
	}

	// 条件查询
	@Override
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public @ResponseBody List<UrlInfo> getUrls(@RequestBody UrlInfo urlInfo) {
		return spliderService.getUrls(urlInfo);
	}

	// 主功能:网络爬虫
	@Override
	@RequestMapping(value = "/splider", method = RequestMethod.POST)
	public @ResponseBody Response splider(@RequestBody SpliderReq spliderReq) {
		return spliderService.splider(spliderReq);
	}

}
