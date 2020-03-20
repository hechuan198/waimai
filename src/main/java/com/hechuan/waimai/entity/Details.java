package com.hechuan.waimai.entity;

import com.hechuan.waimai.util.VeDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
public class Details {
	private String detailsid = "D" + VeDate.getStringId();
	private String userId;
	private String ordercode;
	private String filmid;
	private Integer num;
	private BigDecimal price;
	private String receiverAddress;
	private String viewdate;
	private String filmname;
	private BigDecimal totalPrice;

}
