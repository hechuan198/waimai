package com.hechuan.waimai.entity;

import com.hechuan.waimai.util.VeDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Orders {
	private String ordersid = "O" + VeDate.getStringId();
	private String ordercode;
	private String usersid;
	private String total;
	private String status;
	private String addtime;
	private String paymentTime;
	private String sendTime;
	private String endtime;
	private String username;


}
