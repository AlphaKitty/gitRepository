package com.dhcc.zhyl.springMVC.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dhcc.zhyl.springMVC.dao.UserDao;
import com.dhcc.zhyl.springMVC.entity.AcctUser;
import com.dhcc.zhyl.springMVC.entity.Response;
import com.dhcc.zhyl.springMVC.tools.EntityCombine;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	// private static final Logger log = Logger.getLogger(UserDaoImpl.class);
	private String sql = "";

	private Session getCurrencySession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AcctUser> showAll() {
		return (List<AcctUser>) this.getCurrencySession().createQuery("from AcctUser").list();
	}

	@Override
	public Response addUser(AcctUser acctUser) {
		try {
			acctUser.setId(UUID.randomUUID().toString());
			acctUser.setRegisterTime(new Date());
			if (null == acctUser.getCardId())
				return new Response("0003", "身份证号不能为空");
			this.getCurrencySession().save(acctUser);
			return new Response("0000", "success");
		} catch (Exception e) {
			return new Response("9999", "未知错误");
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public Response delUser(AcctUser acctUser) {
		try {
			List<AcctUser> list = this.getCurrencySession().createQuery("from AcctUser where cardId = '" + acctUser.getCardId() + "'").list();
			if (0 == list.size())
				return new Response("0003", "找不到此条记录");
			for (AcctUser temp : list) {
				this.getCurrencySession().delete(temp);
			}
			return new Response("0000", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Response("9999", "操作失败");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response altUser(AcctUser acctUser) {
		try {
			List<AcctUser> list = this.getCurrencySession().createQuery("from AcctUser where cardId = '" + acctUser.getCardId() + "'").list();
			if (0 == list.size())
				return new Response("0003", "找不到此条记录");
			AcctUser combineUser = (AcctUser) EntityCombine.combine(acctUser, list.get(0));
			this.getCurrencySession().clear();
			this.getCurrencySession().update(combineUser);
			return new Response("0000", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Response("9999", "操作失败");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AcctUser> findUser(AcctUser acctUser) {
		sql = "from AcctUser where 1=1 ";
		if (null != acctUser.getNickName())
			sql += " and nickName = '" + acctUser.getNickName() + "' ";
		if (null != acctUser.getTelephone())
			sql += " and telephone = '" + acctUser.getTelephone() + "' ";
		if (null != acctUser.getCardId())
			sql += " and cardId = '" + acctUser.getCardId() + "' ";
		System.out.println(sql);
		List<AcctUser> list = this.getCurrencySession().createQuery(sql).list();
		return list;
	}

}
