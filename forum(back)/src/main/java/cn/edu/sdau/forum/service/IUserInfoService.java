package cn.edu.sdau.forum.service;

import java.util.List;

import cn.edu.sdau.forum.po.Userinfo;

public interface IUserInfoService {
	//新增或修改
    public boolean saveOrUpdate(Userinfo userinfo);
    //条件查询
	public List<Userinfo> queryByCondition(Userinfo userinfo);
	//根据pid查询
//	public Userinfo queryByPid(Userinfo userinfo);
	//根据uid查询
//	public List<Userinfo> queryByUid(Userinfo userinfo);
}
