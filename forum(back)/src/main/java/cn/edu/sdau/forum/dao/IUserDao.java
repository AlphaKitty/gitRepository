package cn.edu.sdau.forum.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.sdau.forum.po.User;

@Repository
public interface IUserDao {
	//新增或修改
    public boolean saveOrUpdate(User user);
    //删除
    public boolean deleteById(User user);
    //按id查询
    public User queryById(User user);
    //按条件查询
    public List<User> queryByCondition(String hql);
    //查询所有用户
	public List<User> queryAllUser(User user);
}
