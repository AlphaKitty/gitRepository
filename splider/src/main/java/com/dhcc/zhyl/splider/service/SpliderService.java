package com.dhcc.zhyl.splider.service;

import java.util.List;

import com.dhcc.zhyl.splider.entity.Response;
import com.dhcc.zhyl.splider.entity.SpliderReq;
import com.dhcc.zhyl.splider.entity.UrlInfo;

public interface SpliderService {

	Response addUrl(UrlInfo urlInfo);

	Response addUrls(List<UrlInfo> urlInfos);

	List<UrlInfo> getAllUrls();

	Response readTxtSaveData(String location);

	List<UrlInfo> getUrls(UrlInfo urlInfo);

	Response splider(SpliderReq spliderReq);

}
