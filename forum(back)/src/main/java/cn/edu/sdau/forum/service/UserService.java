package cn.edu.sdau.forum.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.sdau.forum.dao.IUserDao;
import cn.edu.sdau.forum.po.User;

@Service
@WebService
@SOAPBinding(style=Style.RPC)
public class UserService implements IUserService{
	
    @Autowired
    private IUserDao userDao;
    public IUserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    
    //新增或修改
    @Override
    public boolean saveOrUpdate(User user) {
        return userDao.saveOrUpdate(user);
    }
    
    //删除
    @Override
    public boolean deleteById(User user) {
        User user2=userDao.queryById(user);
        return userDao.deleteById(user2);
    }
    
    //查询
    @Override
    public User queryById(User user) {
        return userDao.queryById(user);
    }
    
    //根据条件查询
    @Override
    public List<User> queryByCondition(User user) {
        String hql="from User u where 1=1 ";
        if(null!=user.getUsername()&&!"".equals(user.getUsername())){
            hql+=" and u.username like '%" + user.getUsername() +"%'";
        }
//        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return this.userDao.queryByCondition(hql);
    }
    
    //登录验证
    @Override
    public boolean queryLogin(User user){
    	String hql="from User u where ";
    	 if(!"".equals(user.getUsername())&&null!=user.getUsername()&&!"".equals(user.getPassword())&&null!=user.getPassword()){
    		 hql+=" u.username = '" + user.getUsername() +"' and";
    		 hql+=" u.password = '" + user.getPassword() +"'";
    		 List<User> list=this.userDao.queryByCondition(hql);
    		 if(null!=list&&list.size()>0){
    		     return true;
    		 }
    		 return false;
    	 }
        return false;
    }
	@Override
	public List<User> queryAllUser(User user) {
		return userDao.queryAllUser(user);
	}
    
//    //根据邮箱查询
//    @Override
//    public User queryByEmail(User user) {
//    	List<User> list=new ArrayList<User>();
//        String hql="from User u where ";
//        if(null!=user.getEmail()&&!"".equals(user.getEmail())){
//            hql+="u.email = '" + user.getEmail() + "'";
//            list = this.userDao.queryByCondition(hql);
//            if(null==list||list.size()<=0){
//            	return null;
//            }else{
//            	return list.get(0);
//            }
//        }else{
//        	return null;
//        }
//    }
    
}
