package cn.edu.sdau.forum.dao;

import java.util.List;

import cn.edu.sdau.forum.po.Reply;

public interface IReplyDao {

	//新增或修改
    public boolean saveOrUpdate(Reply reply);
    //删除
    public boolean deleteById(Reply reply);
    //按rid查询
    public Reply queryByRid(Reply reply);
    //按pid查询
    public List<Reply> queryByPid(String hql);
    //按条件查询
    public List<Reply> queryByCondition(String hql);
}
