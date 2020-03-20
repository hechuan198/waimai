package com.hechuan.waimai.service;

import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.User;
import com.hechuan.waimai.dto.UserDTO;
import com.hechuan.waimai.util.ResultVO;

import java.util.List;

public interface UserService {
    /**
     * 登录验证
     * @param username
     * @return
     */
    UserDTO getUserByUsername(String username, Integer role);

    /**
     * 注册验证用户名重复
     * @param username
     * @return
     */
    ResultVO getConfirmUsername(String username);

    /**
     * 注册
     * @param userDTO
     * @return
     */
    ResultVO register(UserDTO userDTO);

    /**
     * 忘记密码，确认用户名
     * @param username
     * @return
     */
    ResultVO getUsername(String username);

    /**
     * 忘记密码，获取密保问题
     * @param username
     * @return
     */
    ResultVO getQuestion(String username);

    /**
     * 忘记密码，验证答案
     * @param username
     * @param answer
     * @return
     */
    ResultVO question(String username,String answer);

    /**
     * 忘记密码，修改密码
     * @param userDTO
     * @return
     */
    ResultVO password(UserDTO userDTO);

    /**
     * 分页查询用户信息
     * @param userDTO
     * @return
     */
    PageInfo<User> queryUserList(UserDTO userDTO);

    ResultVO updateUserStatus(UserDTO userDTO);

    /**
     * 查询每月用户数
     * @return
     */
    List<UserDTO> queryUserByMonth();

}
