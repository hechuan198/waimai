package com.hechuan.waimai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dao.UserMapper;
import com.hechuan.waimai.dto.User;
import com.hechuan.waimai.dto.UserDTO;
import com.hechuan.waimai.service.UserService;
import com.hechuan.waimai.util.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDTO getUserByUsername(String username) {

        UserDTO userDTO = userMapper.getUserByUsername(username);

//        封装数据
       /* if (null != userDTO){
            UserDTO userDTO1 = transform(userDTO);
            return userDTO1;
        }
*/
        return userDTO;
    }

    @Override
    public ResultVO getConfirmUsername(String username) {

        UserDTO userDTO = userMapper.getUserByUsername(username);
        if (userDTO != null){
            return ResultVO.error("用户已存在");
        }

        return ResultVO.success("用户名可用");
    }

    @Override
    public ResultVO register(UserDTO userDTO) {
        userDTO.setRole(2);
        userMapper.insertUser(userDTO);
        return ResultVO.success("注册成功");
    }

    @Override
    public ResultVO getUsername(String username) {

        UserDTO userDTO = userMapper.getUserByUsername(username);
        if (null == userDTO){
            return ResultVO.error("用户名不存在");
        }
        String getusername = userDTO.getUsername();
        return ResultVO.success("用户名存在",getusername);
    }

    @Override
    public ResultVO getQuestion(String username) {

        UserDTO userDTO = userMapper.getUserByUsername(username);
        if (null == userDTO){
            return ResultVO.error("问题查询错误");
        }
        String question = userDTO.getQuestion();
        return ResultVO.success("问题查询成功",question);
    }

    @Override
    public ResultVO question(String username, String answer) {

        UserDTO userDTO = userMapper.getUserByUsernameAndAnswer(username, answer);
        if (userDTO == null){
            return ResultVO.error("答案错误");
        }
        String getusername = userDTO.getUsername();
        return ResultVO.success("答案正确",getusername);

    }

    @Override
    public ResultVO password(UserDTO userDTO) {

        userMapper.updatePassword(userDTO);

        return ResultVO.success("修改密码成功");
    }

    /**
     * 分页查询用户信息
     * @param userDTO
     * @return
     */
    @Override
    public PageInfo<User> queryUserList(UserDTO userDTO) {
        return PageHelper.startPage(userDTO.getPageNum(),userDTO.getPageSize()).doSelectPageInfo(() -> userMapper.queryUserList(userDTO));
    }

    @Override
    public ResultVO updateUserStatus(UserDTO userDTO) {

        userMapper.updateByPrimaryKeySelective(userDTO);

        return ResultVO.success("冻结解冻成功");
    }
/*
    private UserDTO transform(UserDTO userDTO){
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setUsername(userDTO.getUsername());
        return userDTO1;
    }*/


}
