package cn.edu.sdau.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.sdau.forum.dao.IUserInfoDao;
import cn.edu.sdau.forum.po.Userinfo;

public class UserInfoService implements IUserInfoService {

	@Autowired
    private IUserInfoDao userInfoDao;
    public IUserInfoDao getUserInfoDao() {
        return userInfoDao;
    }
    public void setUserInfoDao(IUserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }
	
	//新增或修改
	@Override
	public boolean saveOrUpdate(Userinfo userinfo) {
		return false;
	}

	//条件查询
	@Override
	public List<Userinfo> queryByCondition(Userinfo userinfo) {
		return null;
	}

}
