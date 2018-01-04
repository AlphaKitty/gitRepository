package cn.edu.sdau.forum.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.sdau.forum.po.Post;

@Repository
public interface IPostDao {
	//新增或修改
    public boolean saveOrUpdate(Post post);
    //删除
    public boolean deleteByPid(Post post);
    //按id查询
    public Post queryById(Post post);
    //按条件查询
    public List<Post> queryByCondition(String hql);
    //查询某个用户的所有帖子
	public List<Post> queryByUid(Post post);
}
