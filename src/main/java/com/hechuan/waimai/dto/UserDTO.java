package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UserDTO {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String rapassword;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 找回密码问题
     */
    private String question;
    /**
     * 答案
     */
    private String answer;
    /**
     * 角色 2：用户
     */
    private Integer role;


}
