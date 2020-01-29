package com.hechuan.waimai.dao;

import com.hechuan.waimai.dto.User;
import com.hechuan.waimai.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    UserDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDTO record);

    int updateByPrimaryKey(User record);


    UserDTO getUserByUsername(String username);

    int insertUser(UserDTO userDTO);

    UserDTO getUserByUsernameAndAnswer(String username,String answer);

    UserDTO updatePassword(UserDTO userDTO);


    Integer queryUserCount();

    List<User> queryUserList(UserDTO userDTO);

}