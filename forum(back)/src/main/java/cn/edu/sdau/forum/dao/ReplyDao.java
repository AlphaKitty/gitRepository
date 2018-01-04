package cn.edu.sdau.forum.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.sdau.forum.po.Reply;

public class ReplyDao implements IReplyDao {
	
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
	public boolean saveOrUpdate(Reply reply) {
		try {
			sessionFactory.getCurrentSession().flush();
			sessionFactory.getCurrentSession().saveOrUpdate(reply);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 根据id删除
	@Override
	public boolean deleteById(Reply reply) {
		return false;
	}

	// 根据rid查询
	@Override
	public Reply queryByRid(Reply reply) {
		try {
			return (Reply) sessionFactory.getCurrentSession().createQuery("from Reply r where r.rid ="+reply.getRid()).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	//根据pid查询
	@Override
	public List<Reply> queryByPid(String hql) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 根据条件查询
	@Override
	public List<Reply> queryByCondition(String hql) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			List<Reply> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
