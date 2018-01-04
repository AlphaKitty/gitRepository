package cn.edu.sdau.forum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.edu.sdau.forum.po.User;

@Service
public interface IUserService {
    //新增或修改
    public boolean saveOrUpdate(User user);
    //删除
    public boolean deleteById(User user);
    //查询
    public User queryById(User user);
    //根据案件查询
    public List<User> queryByCondition(User user);
    //登录验证
	public boolean queryLogin(User user);
//	//根据邮箱查询
//	public User queryByEmail(User user);
	//查询所有用户
	public List<User> queryAllUser(User user);
}
