package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.CartDAO;
import com.hechuan.waimai.entity.Cart;
import com.hechuan.waimai.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {
	@Autowired
	@Resource
	private CartDAO cartDAO;

	@Override // 继承接口的新增 返回值0(失败),1(成功)
	public int insertCart(Cart cart) {
		return this.cartDAO.insertCart(cart);
	}


	@Override // 继承接口的删除 返回值0(失败),1(成功)
	public int deleteCart(String cartid) {
		return this.cartDAO.deleteCart(cartid);
	}


	@Override // 继承接口的按条件精确查询
	public List<Cart> getCartByCond(Cart cart) {
		return this.cartDAO.getCartByCond(cart);
	}



}
