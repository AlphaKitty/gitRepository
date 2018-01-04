package cn.edu.sdau.forum.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.sdau.forum.po.Userinfo;

public class UserInfoDao implements IUserInfoDao {

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
	public boolean saveOrUpdate(Userinfo userinfo) {
		return false;
	}

	//删除
	@Override
	public boolean deleteById(Userinfo userinfo) {
		return false;
	}

	//按id查询
	@Override
	public Userinfo queryById(Userinfo userinfo) {
		return null;
	}

	//按条件查询
	@Override
	public List<Userinfo> queryByCondition(String hql) {
		return null;
	}

}
