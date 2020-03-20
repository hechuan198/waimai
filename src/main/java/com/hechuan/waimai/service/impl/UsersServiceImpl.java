package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.UsersDAO;
import com.hechuan.waimai.entity.Users;
import com.hechuan.waimai.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
	@Autowired
	@Resource
	private UsersDAO usersDAO;


	@Override // 继承接口的更新 返回值0(失败),1(成功)
	public int updateUsers(Users users) {
		return this.usersDAO.updateUsers(users);
	}


	@Override // 继承接口的按条件精确查询
	public List<Users> getUsersByCond(Users users) {
		return this.usersDAO.getUsersByCond(users);
	}


	@Override // 继承接口的按主键查询 返回Entity实例
	public Users getUsersById(String usersid) {
		return this.usersDAO.getUsersById(usersid);
	}

}
