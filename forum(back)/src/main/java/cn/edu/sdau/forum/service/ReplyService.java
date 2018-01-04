package cn.edu.sdau.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.sdau.forum.dao.IPostDao;
import cn.edu.sdau.forum.dao.IReplyDao;
import cn.edu.sdau.forum.dao.IUserDao;
import cn.edu.sdau.forum.po.Post;
import cn.edu.sdau.forum.po.Reply;
import cn.edu.sdau.forum.po.User;

public class ReplyService implements IReplyService {

	@Autowired
	private IReplyDao replyDao;
	@Autowired
	private IPostDao postDao;
	@Autowired
	private IUserDao userDao;

	public IReplyDao getReplyDao() {
		return replyDao;
	}
	public void setReplyDao(IReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	public IPostDao getPostDao() {
		return postDao;
	}
	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	//保存或更新
	@Override
	public boolean saveOrUpdate(Reply reply) {
		User tempUser = new User();
		tempUser = userDao.queryById(reply.getUser());
		Post tempPost = new Post();
		tempPost = postDao.queryById(reply.getPost());
		reply.setUser(tempUser);
		reply.setPost(tempPost);
		return replyDao.saveOrUpdate(reply);
	}

	//条件查询
	@Override
	public List<Reply> queryByCondition(Reply reply) {
		return null;
	}

	//按rid查询
	@Override
	public Reply queryByRid(Reply reply) {
		return replyDao.queryByRid(reply);
	}
	
	//按pid查询
	@Override
	public List<Reply> queryByPid(Reply reply) {
		Post post = new Post();
		post=postDao.queryById(reply.getPost());
		reply.setPost(post);
		String hql = "from Reply r where 1=1";
		if(null!=reply.getPost()){
			hql+=" and r.post.pid="+reply.getPost().getPid();
		}else{
			hql="from Reply r where 1=2";
		}
		return this.replyDao.queryByPid(hql);
	}
}
