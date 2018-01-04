package cn.edu.sdau.forum.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sdau.forum.po.User;

@Repository
public class UserDao implements IUserDao{
	
    @Autowired
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //新增或修改
    @Override
    public boolean saveOrUpdate(User user) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    //删除
    @Override
    public boolean deleteById(User user) {
        try {
            sessionFactory.getCurrentSession().createQuery("delete User u where u.uid= "+user.getUid()).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
        return true;
    }

    //根据id查询
    @Override
    public User queryById(User user) {
        try {
            return (User) sessionFactory.getCurrentSession().createQuery("from User u where u.uid ="+user.getUid()).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //按条件查询
    @SuppressWarnings("unchecked")
    @Override
    public List<User> queryByCondition(String hql) {
        try {
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            List<User> list=query.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //查询所有用户
	@Override
	public List<User> queryAllUser(User user) {
		Query tempResult = sessionFactory.getCurrentSession().createQuery("from User u where 1=1");
		List<User> users = tempResult.list();
		return users;
	}
}
