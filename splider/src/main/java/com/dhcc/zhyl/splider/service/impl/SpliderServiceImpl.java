package com.dhcc.zhyl.splider.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.zhyl.splider.dao.SpliderDao;
import com.dhcc.zhyl.splider.entity.Response;
import com.dhcc.zhyl.splider.entity.SpliderReq;
import com.dhcc.zhyl.splider.entity.UrlInfo;
import com.dhcc.zhyl.splider.service.SpliderService;

@Transactional
@Service("spliderServiceImpl")
public class SpliderServiceImpl implements SpliderService {

	private static final Logger log = Logger.getLogger(SpliderServiceImpl.class);

	@Autowired
	private SpliderDao spliderDao;

	@Override
	public Response addUrl(UrlInfo urlInfo) {
		return spliderDao.addUrlInfo(urlInfo);
	}

	@Override
	public Response addUrls(List<UrlInfo> urlInfos) {
		return spliderDao.addUrlInfos(urlInfos);
	}

	@Override
	public List<UrlInfo> getAllUrls() {
		return spliderDao.getAllUrls();
	}

	@Override
	public Response readTxtSaveData(String location) {
		log.info("***************文件地址:" + location + "****************");
		return spliderDao.readTxtSaveData(location);
	}

	@Override
	public List<UrlInfo> getUrls(UrlInfo urlInfo) {
		return spliderDao.getUrls(urlInfo);
	}

	@Override
	// 网络爬虫方法入口 接受参数为只有一个网址和时长的实体
	public Response splider(SpliderReq spliderReq) {
		long lasts = 0L;
		try {
			lasts = Long.parseLong(spliderReq.getDuration());
		} catch (NumberFormatException e) {
			return new Response("0011", "输入正确的时长!");
		}
		if (null == spliderReq.getUrl() || "" == spliderReq.getUrl().trim())
			return new Response("0008", "抓取路径不能为空");
		else if (spliderReq.getUrl().startsWith("www"))
			return new Response("0009", "请输入带网络协议的网址");
		if (10000 > lasts)
			return new Response("0010", "抓取时长太短,至少为10秒");
		log.info("路径地址:" + spliderReq.getUrl());
		String originUrl = spliderReq.getUrl();
		String htmlTxt = "G:\\Users\\Administrator\\workspace\\workspace\\splider\\src\\myselfHtml.txt";
		String urlsTxt = "G:\\Users\\Administrator\\workspace\\workspace\\splider\\src\\myselfUrls.txt";
		List<String> urls = new ArrayList<>();
		long begin = System.currentTimeMillis();

		// ① 找到一个网址并覆盖下载其HTML内容到本地
		Response response1 = fetchHtml(originUrl, htmlTxt);
		if (!"0000".equals(response1.getErrorCode()))
			return response1;

		// TODO
		try {
			@SuppressWarnings("resource")
			FileWriter file = new FileWriter(urlsTxt, false);
			file.write("");
		} catch (IOException e) {
			return new Response("000", "文件未找到");
		}

		// ② 分析并抓取HTML中的地址 地址放在一个链表中 并将他们追加到一个新的本地文档
		urls = findUrls(htmlTxt, urlsTxt, lasts, begin);
		if (null == urls)
			return new Response("0006", "网址列表获取失败");

		// ③ 逐个分析并重复①②步骤
		repeat(urls, htmlTxt, urlsTxt, lasts, begin);

		readTxtSaveData(urlsTxt);

		return new Response("0000", "操作成功");
	}

	public void repeat(List<String> urls, String htmlTxt, String urlsTxt, long lasts, long begin) {

		for (String url : urls) {
			long end = System.currentTimeMillis();
			fetchHtml(url, htmlTxt);
			List<String> repeatUrls = findUrls(htmlTxt, urlsTxt, lasts, begin);
			repeat(repeatUrls, htmlTxt, urlsTxt, lasts, begin);
			System.out.println("*****************搜索下一个页面*****************");
			if (lasts < end - begin) {
				log.info("*********************************截止时间到,结束抓取**************************************");
				break;
			}
		}
	}

	public List<String> findUrls(String htmlTxt, String urlsTxt, long lasts, long begin) {
		// 声明输入流
		InputStream in = null;
		FileWriter file = null;
		BufferedReader br = null;
		List<String> urls = new ArrayList<>();

		try {
			// 实例化IO流，允许文件追加写
			file = new FileWriter(urlsTxt, true);
			in = new FileInputStream(htmlTxt);
			br = new BufferedReader(new InputStreamReader(in));
			// 开始解析html
			while (br.readLine() != null) {
				long end = System.currentTimeMillis();
				if (lasts <= end - begin)
					break;
				// 网址标签
				String name = "";
				String line = br.readLine();
				Pattern pattern = null;
				Matcher matcher = null;
				// 创建正则表达式
				pattern = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
				// 创建匹配器
				if (null != line)
					matcher = pattern.matcher(line);
				else
					continue;
				// 开始与正则表达式进行匹配
				while (matcher.find()) {

					long end1 = System.currentTimeMillis();
					if (lasts <= end1 - begin)
						break;

					String str = matcher.group(1);
					// 跳过链到本页面内链接和无效链接
					if (str.length() < 1) {
						continue;
					}

					if (str.charAt(0) == '#') {
						continue;
					}

					if (str.startsWith("/")) {
						continue;
					}

					if (str.indexOf("mailto:") != -1) {
						continue;
					}
					if (str.toLowerCase().indexOf("javascript") != -1) {
						continue;
					}

					if (str.startsWith("'")) {
						continue;
					}
					// 将有效链接打印到屏幕
					System.out.println(str);
					// 将有效链接写入到文件
					Document doc = null;
					try {
						doc = Jsoup.connect(str).post();
					} catch (Exception e) {
						System.out.println("不能访问的网址!\n");
						continue;
					}
					if (null == doc)
						continue;
					name = doc.getElementsByTag("title").toString().replaceAll("<title>", "").replaceAll("</title>", "").trim();
					file.write(str + " ----------> " + name + "\r\n");
					System.out.println(name + "\n");
					urls.add(str);
				}

			}

		} catch (Exception e) {
			System.out.println("无效链接！！");
		} finally {
			// 关闭IO流
			try {
				if (file != null)
					file.close();
				if (br != null)
					br.close();
				if (in != null)
					in.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return urls;
	}

	@SuppressWarnings("resource")
	public Response fetchHtml(String originUrl, String htmlTxt) {
		URLConnection con = null;
		InputStream in = null;
		int len = 0;
		FileOutputStream out;
		try {
			if (originUrl.startsWith("www"))
				return new Response("0007", "请填写正确地网络协议");
			out = new FileOutputStream(htmlTxt);
			URL url = new URL(originUrl);
			con = url.openConnection();
			con.connect();
			in = con.getInputStream();
			byte[] b = new byte[1024];
			while ((len = in.read(b, 0, 1024)) != -1) {
				out.write(b, 0, len);
			}
			return new Response("0000", "操作成功");
		} catch (FileNotFoundException e) {
			return new Response("0004", "不存在的文件位置");
		} catch (MalformedURLException e) {
			return new Response("0005", "无法访问的网址");
		} catch (IOException e) {
			return new Response("0006", "无法连接的网址");
		}

	}

}
