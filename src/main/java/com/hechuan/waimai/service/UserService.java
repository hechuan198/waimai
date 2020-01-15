package com.hechuan.waimai.service;

import com.hechuan.waimai.dto.UserDTO;
import com.hechuan.waimai.util.ResultVO;

public interface UserService {
    /**
     * 登录验证
     * @param username
     * @return
     */
    UserDTO getUserByUsername(String username);

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

}
