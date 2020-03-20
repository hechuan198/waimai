package com.hechuan.waimai.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@ToString
public class Users {
	private String usersid;
	private String username;
	private String password;
	@Email(message = "邮箱格式不符合")
	private String email;
	@Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式不符合")
	private String phone;
	private String question;
	private Integer role;
	private String answer;
	private String createTime;
	private String updateTime;
	private Integer status;


}
