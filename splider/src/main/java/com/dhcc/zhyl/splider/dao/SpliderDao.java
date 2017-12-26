package com.dhcc.zhyl.splider.dao;

import java.util.List;

import com.dhcc.zhyl.splider.entity.Response;
import com.dhcc.zhyl.splider.entity.UrlInfo;

public interface SpliderDao {

	Response addUrlInfo(UrlInfo urlInfo);

	Response addUrlInfos(List<UrlInfo> urlInfos);

	List<UrlInfo> getAllUrls();

	Response readTxtSaveData(String location);

	List<UrlInfo> getUrls(UrlInfo urlInfo);

}
