package com.dhcc.zhyl.splider.controller;

import java.util.List;

import com.dhcc.zhyl.splider.entity.Response;
import com.dhcc.zhyl.splider.entity.SpliderReq;
import com.dhcc.zhyl.splider.entity.UrlInfo;

public interface Splider {
	Response addUrl(UrlInfo urlInfo);

	Response addUrls(List<UrlInfo> urlInfos);

	List<UrlInfo> getAllUrls();

	Response readTxtSaveData();

	List<UrlInfo> getUrls(UrlInfo urlInfo);

	Response splider(SpliderReq spliderReq);
}
