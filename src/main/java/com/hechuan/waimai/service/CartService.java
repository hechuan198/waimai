package com.hechuan.waimai.service;

import com.hechuan.waimai.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public interface CartService {
	// 插入数据 调用cartDAO里的insertCart配置
	public int insertCart(Cart cart);

	// 删除数据 调用cartDAO里的deleteCart配置
	public int deleteCart(String cartid);


	// 按照Cart类里面的字段名称精确查询 调用cartDAO里的getCartByCond配置
	public List<Cart> getCartByCond(Cart cart);


}
