package com.hechuan.waimai.service;

import com.hechuan.waimai.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usersService")
public interface UsersService {

	// 更新数据 调用usersDAO里的updateUsers配置
	public int updateUsers(Users users);



	// 按照Users类里面的字段名称精确查询 调用usersDAO里的getUsersByCond配置
	public List<Users> getUsersByCond(Users users);


	// 按主键查询表返回单一的Users实例 调用usersDAO里的getUsersById配置
	public Users getUsersById(String usersid);

}
