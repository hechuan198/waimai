package com.hechuan.waimai.dto;

import com.hechuan.waimai.util.VeDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@ToString
@NoArgsConstructor
public class User {
    private String id = "U" + VeDate.getStringId();

    private String username;

    private String password;

    private String email;

    private String phone;

    private String question;

    private String answer;

    private Integer role;

    private Integer status;

    private String createTime;

    private String updateTime;

}