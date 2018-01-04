package cn.edu.sdau.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.sdau.forum.dao.IPostDao;
import cn.edu.sdau.forum.dao.IUserDao;
import cn.edu.sdau.forum.po.Post;
import cn.edu.sdau.forum.po.User;

public class PostService implements IPostService {

	@Autowired
	private IPostDao postDao;
	public IPostDao getPostDao() {
		return postDao;
	}
	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}
	@Autowired
	private IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	//调用一下userDao中的根据uid查询的方法,替换掉只有uid的user,改user是post的一个属性
	public boolean saveOrUpdate(Post post) {
		User tempUser = new User();
		tempUser = userDao.queryById(post.getUser());
		post.setUser(tempUser);
		return postDao.saveOrUpdate(post);
	}
//	@Override
//	public List<Post> queryByCondition(Post post) {
//		return null;
//	}
	
	//根据条件查询
    @Override
    public List<Post> queryByCondition(Post post) {
        String hql="from Post p where 1=1 ";
        if(null!=post.getUser()&&!"".equals(post.getUser())){
            hql+=" and p.user like '%" + post.getUser() +"%'";
        }
        if(null!=post.getTitle()&&!"".equals(post.getTitle())){
            hql+=" and p.title like '%" + post.getTitle() +"%'";
        }
        if(null!=post.getPostTime()&&!"".equals(post.getPostTime())){
            hql+=" and p.postTime like '%" + post.getPostTime() +"%'";
        }
        if(null!=post.getContent()&&!"".equals(post.getContent())){
            hql+=" and p.content like '%" + post.getContent() +"%'";
        }
        return this.postDao.queryByCondition(hql);
    }
    
    //根据pid查询
	@Override
	public Post queryByPid(Post post) {
		return postDao.queryById(post);
	}
	
	//根据uid查询
	@Override
	public List<Post> queryByUid(Post post) {
		return postDao.queryByUid(post);
	}
	
	//删除帖子
	@Override
	public boolean deletePost(Post post) {
		return postDao.deleteByPid(post);
	}

}
