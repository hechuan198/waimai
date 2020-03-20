package com.hechuan.waimai.entity;

import com.hechuan.waimai.util.VeDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
public class Cart {
	private String cartid = "C" + VeDate.getStringId();
	private String usersid;
	private String filmid;
	private Integer num;
	private BigDecimal price;
	private String username;
	private String filmname;
	private String image;


}
