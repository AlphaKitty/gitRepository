package cn.edu.sdau.forum.service;

import java.util.List;

import cn.edu.sdau.forum.po.Post;

public interface IPostService {
	//新增或修改
    public boolean saveOrUpdate(Post post);
    //条件查询
	public List<Post> queryByCondition(Post post);
	//根据pid查询
	public Post queryByPid(Post post);
	//根据uid查询
	public List<Post> queryByUid(Post post);
	//删除帖子
	public boolean deletePost(Post post);
}
