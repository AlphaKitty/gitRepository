package cn.edu.sdau.forum.service;

import java.util.List;

import cn.edu.sdau.forum.po.Reply;

public interface IReplyService {
	//新增或修改
    public boolean saveOrUpdate(Reply reply);
    //条件查询
	public List<Reply> queryByCondition(Reply reply);
	//根据rid查询
	public Reply queryByRid(Reply reply);
	//根据pid查询
	public List<Reply> queryByPid(Reply reply);
}
