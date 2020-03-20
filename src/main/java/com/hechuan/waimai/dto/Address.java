package com.hechuan.waimai.dto;

import com.hechuan.waimai.util.VeDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@Data
@NoArgsConstructor
@ToString
public class Address {
    private String id = "A" + VeDate.getStringId();
    private String userId;
    @NotEmpty(message = "收货姓名不能为空")
    private String receiverName;
    @NotEmpty(message = "收货固定电话不能为空")
    @Pattern(regexp = "^[0-9]*$", message = "收货固定电话格式错误")
    private String receiverPhone;
    @NotEmpty(message = "收货移动电话不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号格式错误")
    private String receiverMobile;
    @NotEmpty(message = "省份不能为空")
    private String receiverProvince;
    @NotEmpty(message = "城市不能为空")
    private String receiverCity;
    @NotEmpty(message = "区/县不能为空")
    private String receiverDistrict;
    @NotEmpty(message = "详细地址不能为空")
    private String receiverAddress;
    @NotEmpty(message = "邮编不能为空")
    @Pattern(regexp = "^[0-9]*$", message = "邮编格式错误")
    private String receiverZip;

    private String createTime;

    private String updateTime;

}