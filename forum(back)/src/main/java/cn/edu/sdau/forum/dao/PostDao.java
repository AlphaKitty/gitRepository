package cn.edu.sdau.forum.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.sdau.forum.po.Post;

public class PostDao implements IPostDao {

	// 自动注入SessionFactory
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 新增或更新
	@Override
	public boolean saveOrUpdate(Post post) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(post);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 根据id删除
	@Override
	public boolean deleteByPid(Post post) {
		try {
            sessionFactory.getCurrentSession().createQuery("delete Post p where p.pid= "+post.getPid()).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
        return true;
	}

	// 根据id查询
	@Override
	public Post queryById(Post post) {
		try {
			return (Post) sessionFactory.getCurrentSession().createQuery("from Post p where p.pid ="+post.getPid()).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 根据条件查询
	@Override
	public List<Post> queryByCondition(String hql) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			List<Post> list = query.list();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	//查询某个用户的所有帖子
	@Override
	public List<Post> queryByUid(Post post) {
		try {
			Query tempResult =  sessionFactory.getCurrentSession().createQuery("from Post p where p.user.uid ="+post.getUser().getUid());
			List<Post> list = tempResult.list();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
