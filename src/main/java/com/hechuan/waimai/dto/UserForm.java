package com.hechuan.waimai.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserForm {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "重复密码不能为空")
    private String rapassword;
    @Email(message = "邮箱格式不符合")
    private String email;
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不符合")
    private String phone;
    @NotEmpty(message = "问题不能为空")
    private String question;
    @NotEmpty(message = "答案不能为空")
    private String answer;


}
