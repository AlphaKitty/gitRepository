package com.dhcc.zhyl.splider.dao.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dhcc.zhyl.splider.dao.SpliderDao;
import com.dhcc.zhyl.splider.entity.Response;
import com.dhcc.zhyl.splider.entity.UrlInfo;

@Repository("spliderDaoImpl")
public class SpliderDaoImpl implements SpliderDao {

	private static final Logger log = Logger.getLogger(SpliderDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Response addUrlInfo(UrlInfo urlInfo) {
		urlInfo.setId(UUID.randomUUID().toString());
		urlInfo.setFetchTime(new Date());
		try {
			this.getCurrentSession().save(urlInfo);
			return new Response("0000", "操作成功");
		} catch (Exception e) {
			return new Response("9999", "未知错误");
		}

	}

	@Override
	public Response addUrlInfos(List<UrlInfo> urlInfos) {
		int sum = 0;
		Map<String, Object> map = new HashMap<>();
		// 查出数据库中所有数据 (数据量如果很大的话 很吃力啊)
		List<UrlInfo> list = getAllUrls();
		// 把数据放到hashmap里 用到的是类似于享元设计模式
		if (null != list && 0 != list.size()) {
			for (UrlInfo temp : list)
				map.put(temp.getUrl(), temp);
		}
		for (UrlInfo temp : urlInfos) {
			if (null != map.get(temp.getUrl()))
				// log.info("***************已存在的数据,不需要再插入***************");
				continue;
			else {
				if (255 < temp.getUrl().length() || 255 < temp.getTitle().length()) {
					log.info("***************title为 " + temp.getTitle() + " 的记录因为长度过长而插入失败***************");
					continue;
				}
				addUrlInfo(temp);
				map.put(temp.getUrl(), temp);
				sum++;
				// log.info("***************第" + sum + "条成功插入***************");
			}
		}
		log.info("***************一共" + urlInfos.size() + "条数据,成功插入了" + sum + "条数据,异常的" + (urlInfos.size() - sum) + "条记录已被忽略***************");
		return new Response("一共" + urlInfos.size() + "条数据", "成功插入了" + sum + "条数据");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UrlInfo> getAllUrls() {
		return this.getCurrentSession().createQuery("from UrlInfo").list();
	}

	@Override
	public Response readTxtSaveData(String location) {
		int s = 0;
		List<UrlInfo> infos = new ArrayList<>();
		// 读取文件取出URL和title
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(location)));
			int emptylines = 0;
			String temp = null;
			while (null != br && null != (temp = br.readLine())) {
				emptylines = 0;
				UrlInfo info = new UrlInfo();
				// 判断文档是否读完 有三行空格算读完
				if ("".equals(temp.trim()) && 3 > emptylines) {
					emptylines++;
					continue;
				}
				if (-1 == temp.indexOf(" ----------> "))
					continue;
				String frontUrl = temp.substring(0, temp.indexOf(" ----------> "));
				String behindTitle = temp.substring(temp.indexOf(" ----------> ") + 13, temp.length());
				info.setUrl(frontUrl);
				info.setTitle(behindTitle);
				System.out.println("\n" + frontUrl + "------>完成读取");
				// System.out.println(behindTitle);
				infos.add(info);
				s++;
				if (100 == s)
					System.out.println("here");
				// log.info("***************第" + s + "条数据完成读取 title:" +
				// info.getTitle() + "***************");
			}
			addUrlInfos(infos);
			return new Response("0000", "一共有" + s + "条数据");
		} catch (FileNotFoundException e) {
			return new Response("0002", "文件不存在");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("这他妈是什么鬼异常");
			return new Response("0003", "鬼异常");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UrlInfo> getUrls(UrlInfo urlInfo) {
		String sql = "from UrlInfo where 1=1 ";
		List<UrlInfo> list = new ArrayList<>();
		if (null != urlInfo.getUrl()) {
			sql += " and url like '%" + urlInfo.getUrl() + "%'";
		}
		if (null != urlInfo.getTitle()) {
			sql += " and title like '%" + urlInfo.getTitle() + "%' ";
		}
		list = this.getCurrentSession().createQuery(sql).list();
		return list;
	}

}
