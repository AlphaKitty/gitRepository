package cn.edu.sdau.forum.dao;

import java.util.List;

import cn.edu.sdau.forum.po.Userinfo;

public interface IUserInfoDao {
	//新增或修改
    public boolean saveOrUpdate(Userinfo userinfo);
    //删除
    public boolean deleteById(Userinfo userinfo);
    //按id查询
    public Userinfo queryById(Userinfo userinfo);
    //按条件查询
    public List<Userinfo> queryByCondition(String hql);
}
