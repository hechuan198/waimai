package com.hechuan.waimai.action;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.hechuan.waimai.dto.Address;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.UserDTO;
import com.hechuan.waimai.dto.UserForm;
import com.hechuan.waimai.entity.*;
import com.hechuan.waimai.service.*;
import com.hechuan.waimai.util.AlipayConfig;
import com.hechuan.waimai.util.ResultVO;
import com.hechuan.waimai.util.VeDate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//定义为控制器
@Controller
// 设置路径
@RequestMapping("/index")
public class IndexAction extends BaseAction {

	@Autowired
	private UserService userService;
	@Autowired
	@Resource
	private UsersService usersService;
	@Autowired
	@Resource
	private CateService cateService;
	@Autowired
	@Resource
	private FilmService filmService;
	@Autowired
	@Resource
	private CartService cartService;
	@Autowired
	@Resource
	private OrdersService ordersService;
	@Autowired
	@Resource
	private DetailsService detailsService;
	@Autowired
	@Resource
	private AddressService addressService;
	// 公共方法 提供公共查询数据
	private void front() {
		this.getRequest().setAttribute("title", "小牛外卖系统");
		List<Cate> cateList = this.cateService.getAllCate();
		this.getRequest().setAttribute("cateList", cateList);
		List<Film> hotList = this.filmService.getFilmByHot();
		this.getRequest().setAttribute("hotList", hotList);
	}

	// 首页显示
	@RequestMapping("index.action")
	public String index() {
		this.front();
		List<Cate> cateList = this.cateService.getCateFront();
		List<Cate> frontList = new ArrayList<Cate>();
		for (Cate cate : cateList) {
			List<Film> flimList = this.filmService.getFilmByCate(cate.getCateid());
			cate.setFlimList(flimList);
			frontList.add(cate);
		}
		this.getRequest().setAttribute("frontList", frontList);
		return "users/index";
	}



	// 准备登录
	@RequestMapping("preLogin.action")
	public String prelogin() {
		this.front();
		return "users/login";
	}

	// 用户登录
	@RequestMapping("login.action")
	public String login() {
		this.front();
		String username = this.getRequest().getParameter("username");
		String password = this.getRequest().getParameter("password");
		Users u = new Users();
		u.setUsername(username);
		List<Users> usersList = this.usersService.getUsersByCond(u);
		if (usersList.size() == 0) {
			this.getSession().setAttribute("message", "用户名不存在");
			return "redirect:/index/preLogin.action";
		} else {
			Users users = usersList.get(0);
			logger.info("【登录，用户信息】 = {}", JSON.toJSONString(users));
			if (password.equals(users.getPassword())) {
				this.getSession().setAttribute("userid", users.getUsersid());
				this.getSession().setAttribute("username", users.getUsername());
				this.getSession().setAttribute("users", users);
				return "redirect:/index/index.action";
			} else {
				this.getSession().setAttribute("message", "密码错误");
				return "redirect:/index/preLogin.action";
			}
		}
	}

	// 准备注册
	@RequestMapping("preReg.action")
	public String preReg() {
		this.front();
		return "users/register";
	}

	/**
	 * 注册
	 * @param userForm
	 * @return
	 */
	@RequestMapping("register.action")
	public String register(@Valid UserForm userForm, BindingResult bindingResult){
		this.front();
		if (null == userForm || StringUtils.isEmpty(userForm.getUsername())){
			this.getSession().setAttribute("message", "用户名不能为空");
			return "redirect:/index/preReg.action";
		}

		System.out.println(userForm);

		ResultVO resultVOUsername = userService.getConfirmUsername(userForm.getUsername());
		if (resultVOUsername.getCode() == "1"){
			this.getSession().setAttribute("message", resultVOUsername.getMessage());
			return "redirect:/index/preReg.action";

		}
		if (bindingResult.hasErrors()){
			String message = bindingResult.getFieldError().getDefaultMessage();
			this.getSession().setAttribute("message", message);
			return "redirect:/index/preReg.action";
		}
        /*if (StringUtils.isEmpty(userDTO.getPassword())){
            return ResultVO.error("密码不能为空");
        }
        if (StringUtils.isEmpty(userDTO.getRapassword())){
            return ResultVO.error("确认密码不能为空");
        }*/
		if (!userForm.getPassword().equals(userForm.getRapassword())){
			this.getSession().setAttribute("message", "两次密码不相同");
			return "redirect:/index/preReg.action";
		}
        /*if (StringUtils.isEmpty(userDTO.getQuestion())){
            return ResultVO.error("问题不能为空");
        }
        if (StringUtils.isEmpty(userDTO.getAnswer())){
            return ResultVO.error("答案不能为空");
        }*/
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userForm,userDTO);
		userService.register(userDTO);
		this.getSession().setAttribute("message", "注册成功");
		return "redirect:/index/preLogin.action";
	}


	// 退出登录
	@RequestMapping("exit.action")
	public String exit() {
		this.front();
		this.getSession().removeAttribute("userid");
		this.getSession().removeAttribute("username");
		this.getSession().removeAttribute("users");
		return "redirect:/index/index.action";
	}

	// 准备修改密码
	@RequestMapping("prePwd.action")
	public String prePwd() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/editpwd";
	}

	// 修改密码
	@RequestMapping("editpwd.action")
	public String editpwd() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		String password = this.getRequest().getParameter("password");
		String newpassword = this.getRequest().getParameter("newpassword");
		String repassword = this.getRequest().getParameter("repassword");
		Users users = this.usersService.getUsersById(userid);
		if(StringUtils.isEmpty(password) || StringUtils.isEmpty(newpassword) || StringUtils.isEmpty(repassword)){
			this.getSession().setAttribute("message", "密码不能为空");
			return "redirect:/index/prePwd.action";
		}
		if (!password.equals(users.getPassword())) {

			this.getSession().setAttribute("message", "旧密码错误");
			return "redirect:/index/prePwd.action";
		}
		if (!newpassword.equals(repassword)) {
			this.getSession().setAttribute("message", "两次密码不一致");
			return "redirect:/index/prePwd.action";
		}
		users.setPassword(repassword);
		this.usersService.updateUsers(users);
		this.getSession().setAttribute("message", "密码修改成功");
		return "redirect:/index/prePwd.action";
	}

	@RequestMapping("usercenter.action")
	public String usercenter() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/usercenter";
	}

	@RequestMapping("userinfo.action")
	public String userinfo() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		logger.info("【获取用户信息,userId】 = {}",userid);
		Users users = this.usersService.getUsersById(userid);
		this.getSession().setAttribute("users", users );
		logger.info("【获取到的用户信息】 = {}",JSON.toJSONString(users));
		return "users/userinfo";
	}

	// 忘记密码，输入用户名
	@RequestMapping("preforget.action")
	public String preforget(){
		return "users/forgetUsername";
	}

	// 忘记密码，验证用户名
	@RequestMapping("forgetUsername.action")
	public String forgetUsername(String username){
		if (!StringUtils.isEmpty(username)){
			getSession().setAttribute("forgetUsername",username);
		}
		username = (String) getSession().getAttribute("forgetUsername");
		if (StringUtils.isEmpty(getSession().getAttribute("forgetUsername") )){
			getSession().setAttribute("message","用户名不能为空");
			return "redirect:/index/preforget.action";
		}
		ResultVO resultVO = userService.getUsername(username);
		if ("1".equals(resultVO.getCode())){
			getSession().setAttribute("message",resultVO.getMessage());
			return "redirect:/index/preforget.action";
		}
		getRequest().setAttribute("user",resultVO.getData());
		return "users/forgetQuestion";
	}

	// 忘记密码，验证答案
	@RequestMapping("forgetQuestion.action")
	public String forgetQuestion(UserDTO userDTO){
		if (userDTO != null && !StringUtils.isEmpty(userDTO.getAnswer())){
			getSession().setAttribute("forgetAnswer",userDTO.getAnswer());
		}
		String answer = (String) getSession().getAttribute("forgetAnswer");
		String username = (String)getSession().getAttribute("forgetUsername");
		if (StringUtils.isEmpty(answer)){
			getSession().setAttribute("message","答案不能为空");
			return "redirect:/index/forgetUsername.action";
		}
		ResultVO resultVO = userService.getUsername(username);
		UserDTO userDTO1 = (UserDTO) resultVO.getData();
		if (!answer.equals(userDTO1.getAnswer())){
			getSession().setAttribute("message","答案错误");
			return "redirect:/index/forgetUsername.action";
		}
		return "users/forgetPassword";

	}


	@RequestMapping("forgetPassword.action")
	public String forgetPassword(String repassword, String newpassword){

		if (!repassword.equals(newpassword)){
			getSession().setAttribute("message","两次密码不相同");
			return "redirect:/index/forgetQuestion.action";
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername((String) getSession().getAttribute("forgetUsername"));
		userDTO.setPassword(newpassword);
		ResultVO resultVO = userService.password(userDTO);
		getSession().setAttribute("message",resultVO.getMessage());
		return "redirect:/index/preLogin.action";

	}

	@RequestMapping("personal.action")
	public String personal(@Valid Users users, BindingResult bindingResult) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		if (bindingResult.hasErrors()){
			String message = bindingResult.getFieldError().getDefaultMessage();
			this.getSession().setAttribute("message", message);
			return "redirect:/index/userinfo.action";
		}
		this.usersService.updateUsers(users);
		this.getSession().setAttribute("message","修改成功");
		return "redirect:/index/userinfo.action";
	}

	// 添加产品到购物车
	@RequestMapping("addcart.action")
	public String addcart() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setFilmid(getRequest().getParameter("goodsid"));
		cart.setNum(Integer.parseInt(getRequest().getParameter("num")));
		BigDecimal numBigDecimal = new BigDecimal(cart.getNum());
		cart.setPrice(new BigDecimal(getRequest().getParameter("price")).multiply(numBigDecimal));
		cart.setUsersid(userid);
		this.cartService.insertCart(cart);
		return "redirect:/index/cart.action";
	}

	// 查看购物车
	@RequestMapping("cart.action")
	public String cart() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setUsersid(userid);
		List<Cart> cartList = this.cartService.getCartByCond(cart);
		this.getRequest().setAttribute("cartList", cartList);
		return "users/cart";
	}

	// 删除购物车中的产品
	@RequestMapping("deletecart.action")
	public String deletecart(String id) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		this.cartService.deleteCart(id);
		return "redirect:/index/cart.action";
	}

	//取消订单
	@RequestMapping("cancelOrder.action")
	public String cancelOrder(String id){
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		logger.info("【取消订单】，订单号 = {}",id);
		Orders orders = new Orders();
		orders.setStatus("已取消");
		orders.setOrdercode(id);
		ordersService.updateOrders(orders);
		getSession().setAttribute("message","取消订单成功！！");
		return "redirect:/index/showOrders.action";
	}

	// 准备结算
	@RequestMapping("preCheckout.action")
	public String preCheckout() {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Cart cart = new Cart();
		cart.setUsersid(userid);
		List<Cart> cartList = this.cartService.getCartByCond(cart);
		if (cartList.size() == 0) {
			this.getSession().setAttribute("message", "请选购商品");
			return "redirect:/index/cart.action";
		}
		Address address = new Address();
		address.setUserId(userid);
		List<Address> addressList = this.addressService.getAddressByCond(address);
		this.getRequest().setAttribute("addressList", addressList);
		return "users/checkout";
	}


	// 结算
	@RequestMapping("checkout.action")
	public String checkout(String id) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		logger.info("【结算请求参数】 = {}",id);
		String userid = (String) this.getSession().getAttribute("userid");
		Cart cart1 = new Cart();
		cart1.setUsersid(userid);
		List<Cart> cartList = this.cartService.getCartByCond(cart1);
		if (cartList.size() == 0) {
			this.getRequest().setAttribute("message", "请选购商品");
			return "redirect:/index/cart.action";
		} else {
			// 获取一个1200-9999的随机数 防止同时提交
			String ordercode = "PD" + VeDate.getStringDatex();
			double total = 0;
			Address address = addressService.queryAddress(id);
			for (Cart cart : cartList) {
				Details details = new Details();
//				details.setDetailsid(VeDate.getStringDatex() + (Math.random() * 9 + 1) * 1200);
				details.setFilmid(cart.getFilmid());
				details.setUserId(userid);
				details.setNum(cart.getNum());
				details.setOrdercode(ordercode);
				details.setPrice(cart.getPrice().divide(new BigDecimal(cart.getNum())));
				details.setReceiverAddress(address.getReceiverAddress());
				details.setViewdate(VeDate.getStringDate());
				details.setFilmname(cart.getFilmname());
				details.setTotalPrice(cart.getPrice());
				this.detailsService.insertDetails(details);
				Film goods = this.filmService.getFilmById(cart.getFilmid());
				goods.setSellnum("" + (Integer.parseInt(goods.getSellnum()) + cart.getNum()));
				this.filmService.updateFilm(goods);
				total += cart.getPrice().doubleValue() * cart.getNum();
				this.cartService.deleteCart(cart.getCartid());
			}
			Orders orders = new Orders();
			orders.setAddtime(VeDate.getStringDate());
			orders.setOrdercode(ordercode);
			orders.setStatus("未付款");
			orders.setTotal("" + total);
			orders.setUsersid(userid);
			this.ordersService.insertOrders(orders);
		}
		return "redirect:/index/showOrders.action";
	}

	// 查看订购
	@RequestMapping("showOrders.action")
	public String showOrders(String number) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Orders orders = new Orders();
		orders.setUsersid(userid);
		List<Orders> ordersList = new ArrayList<Orders>();
		List<Orders> tempList = this.ordersService.getOrdersByCond(orders);

		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Orders o = tempList.get(i);
			ordersList.add(o);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/showOrders.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("ordersList", ordersList);
		logger.info("【查询用户订单，结果】 = {}", JSON.toJSONString(ordersList));
		return "users/orderlist";
	}

	// 查看地址
	@RequestMapping("showAddress.action")
	public String showAddress(String number) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		String userid = (String) this.getSession().getAttribute("userid");
		Address address = new Address();
		address.setUserId(userid);
		List<Address> addressList = new ArrayList<>();
		List<Address> tempList = this.addressService.getAddressByCond(address);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Address o = tempList.get(i);
			addressList.add(o);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/showAddress.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/showAddress.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/showAddress.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/showAddress.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("addressList", addressList);
		logger.info("【查询地址，结果】 = {}", JSON.toJSONString(addressList));
		return "users/addresslist";
	}

	//准备添加地址
	@RequestMapping("preAddAddress.action")
	public String preAddAddress(){
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		return "users/addAddress";

	}
	//添加地址
	@RequestMapping("addAddress.action")
	public String addAddress(@Valid Address address,  BindingResult bindingResult) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		if (bindingResult.hasErrors()){
			String message = bindingResult.getFieldError().getDefaultMessage();
			this.getSession().setAttribute("message", message);
			return "users/addAddress";
		}
		address.setUserId((String) getSession().getAttribute("userid"));
		logger.info("【请求插入地址信息】 = {}", JSON.toJSONString(address));
		addressService.addAddress(address);
		logger.info("【插入完成】");
		this.getSession().setAttribute("message", "添加地址成功");
		return "redirect:/index/showAddress.action";
	}

	//删除地址
	@RequestMapping("addressDelete.action")
	public String addressDelete(String id){
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		logger.info("【删除地址】，请求参数 = {}", JSON.toJSONString(id));
		addressService.deleteAddress(id);
		logger.info("【删除完成】");
		this.getSession().setAttribute("message", "删除地址成功");
		return "redirect:/index/showAddress.action";
	}

	// 准备修改地址
	@RequestMapping("preAddressUpdate.action")
	public String preAddressUpdate(String id){
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		logger.info("【准备修改地址】，请求参数 = {}", JSON.toJSONString(id));
		Address address = addressService.queryAddress(id);
		this.getRequest().setAttribute("address", address);
		logger.info("【准备修改地址】，查询结果 = {}", JSON.toJSONString(address));
		return "users/updateAddress";
	}
	//修改地址
	@RequestMapping("addressUpdate.action")
	public String addressUpdate(@Valid Address address,  BindingResult bindingResult) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		if (bindingResult.hasErrors()){
			String message = bindingResult.getFieldError().getDefaultMessage();
			this.getSession().setAttribute("message", message);
			return "redirect:/index/preAddressUpdate.action?id="+address.getId();
		}
		logger.info("【修改地址】，请求参数 = {}", JSON.toJSONString(address));
		addressService.updateAddress(address);
		logger.info("【修改完成】");
		this.getSession().setAttribute("message", "修改地址成功");
		return "redirect:/index/showAddress.action";
	}


	@RequestMapping("/pay.action")
	public void payController(HttpServletResponse response,String id) throws IOException {
		logger.info("##{}【付款开始】",id);
		getSession().setAttribute("orderNo",id);
		Orders orders = ordersService.getOrdersById(id);
		logger.info("【准备付款的订单信息】 = {}", JSON.toJSONString(orders));

		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(orders.getOrdercode().getBytes("ISO-8859-1"), "UTF-8");
		//付款金额，必填
		String total_amount = new String(orders.getTotal().getBytes("ISO-8859-1"), "UTF-8");
		//订单名称，必填
		String subject = new String(orders.getOrdersid().getBytes("ISO-8859-1"), "UTF-8");
		//商品描述，可空
		String body = new String(orders.getOrdercode().getBytes("ISO-8859-1"), "UTF-8");

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
				+ "\"total_amount\":\"" + total_amount + "\","
				+ "\"subject\":\"" + subject + "\","
				+ "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		//		+ "\"total_amount\":\""+ total_amount +"\","
		//		+ "\"subject\":\""+ subject +"\","
		//		+ "\"body\":\""+ body +"\","
		//		+ "\"timeout_express\":\"10m\","
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		//请求
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
		response.getWriter().write(form);//直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
	}

	@RequestMapping("payed.action")
	public String payed(){
		String orderNo = (String) getSession().getAttribute("orderNo");
		Orders orders = new Orders();
		orders.setOrdercode(orderNo);
		orders.setStatus("已付款");
		ordersService.updateOrders(orders);
		logger.info("【订单已支付】");
		return "redirect:/index/showOrders.action";

	}

//	// 取消订单
//	@RequestMapping("cancel.action")
//	public String cancel(String id) {
//		this.front();
//		if (this.getSession().getAttribute("userid") == null) {
//			return "redirect:/index/preLogin.action";
//		}
//		Orders orders = this.ordersService.getOrdersById(this.getRequest().getParameter("id"));
//		orders.setStatus("已取消");
//		this.ordersService.updateOrders(orders);
//		return "redirect:/index/showOrders.action";
//	}

	// 订单明细
	@RequestMapping("orderdetail.action")
	public String orderdetail(String id) {
		this.front();
		if (this.getSession().getAttribute("userid") == null) {
			return "redirect:/index/preLogin.action";
		}
		logger.info("【查询订单详情】，请求参数 = {}", id);
		Details details = new Details();
		details.setOrdercode(id);
		List<Details> detailsList = this.detailsService.getDetailsByCond(details);
		logger.info("【查询订单详情】,结果 = {}", JSON.toJSONString(detailsList));
		this.getRequest().setAttribute("detailsList", detailsList);
		return "users/orderdetail";
	}

	// 按分类查询
	@RequestMapping("cate.action")
	public String cate(String id, String number) {
		this.front();
		Film goods = new Film();
		goods.setCateid(id);
		List<Film> flimList = new ArrayList<Film>();
		List<Film> tempList = this.filmService.getFilmByCond(goods);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Film x = tempList.get(i);
			flimList.add(x);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/cate.action?number=0&id=\" + id+ \"\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/cate.action?number=" + (Integer.parseInt(number) - 1) + "&id=\" + id+ \"\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/cate.action?number=" + (Integer.parseInt(number) + 1) + "&id=\" + id+ \"\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/cate.action?number=" + (maxPage - 1) + "&id=\" + id+ \"\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("flimList", flimList);
		return "users/list";
	}

	// 推荐产品
	@RequestMapping("recommend.action")
	public String recommend(String number) {
		this.front();
		Film goods = new Film();
		goods.setRecommend(1);
		List<Film> flimList = new ArrayList<Film>();
		List<Film> tempList = this.filmService.getFilmByCond(goods);
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Film x = tempList.get(i);
			flimList.add(x);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/recommend.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/recommend.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/recommend.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/recommend.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("flimList", flimList);
		return "users/list";
	}

	// 全部产品
	@RequestMapping("all.action")
	public String all(String number) {
		this.front();
		List<Film> flimList = new ArrayList<Film>();
		List<Film> tempList = this.filmService.getAllFilm();
		int pageNumber = tempList.size();
		int maxPage = pageNumber;
		if (maxPage % 12 == 0) {
			maxPage = maxPage / 12;
		} else {
			maxPage = maxPage / 12 + 1;
		}
		if (number == null) {
			number = "0";
		}
		int start = Integer.parseInt(number) * 12;
		int over = (Integer.parseInt(number) + 1) * 12;
		int count = pageNumber - over;
		if (count <= 0) {
			over = pageNumber;
		}
		for (int i = start; i < over; i++) {
			Film x = tempList.get(i);
			flimList.add(x);
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("&nbsp;&nbsp;共为");
		buffer.append(maxPage);
		buffer.append("页&nbsp; 共有");
		buffer.append(pageNumber);
		buffer.append("条&nbsp; 当前为第");
		buffer.append((Integer.parseInt(number) + 1));
		buffer.append("页 &nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("首页");
		} else {
			buffer.append("<a href=\"index/recommend.action?number=0\">首页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if ((Integer.parseInt(number) + 1) == 1) {
			buffer.append("上一页");
		} else {
			buffer.append("<a href=\"index/recommend.action?number=" + (Integer.parseInt(number) - 1) + "\">上一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("下一页");
		} else {
			buffer.append("<a href=\"index/recommend.action?number=" + (Integer.parseInt(number) + 1) + "\">下一页</a>");
		}
		buffer.append("&nbsp;&nbsp;");
		if (maxPage <= (Integer.parseInt(number) + 1)) {
			buffer.append("尾页");
		} else {
			buffer.append("<a href=\"index/recommend.action?number=" + (maxPage - 1) + "\">尾页</a>");
		}
		html = buffer.toString();
		this.getRequest().setAttribute("html", html);
		this.getRequest().setAttribute("flimList", flimList);
		return "users/list";
	}

	// 查询商品
	@RequestMapping("query.action")
	public String query(String name) {
		this.front();
		Film goods = new Film();
		goods.setFilmname(name);
		List<Film> flimList = this.filmService.getFilmByLike(goods);
		this.getRequest().setAttribute("flimList", flimList);
		return "users/list";
	}

	// 商品详情
	@RequestMapping("detail.action")
	public String detail(String id) {
		this.front();
		Film goods = this.filmService.getFilmById(id);
		goods.setHits("" + (Integer.parseInt(goods.getHits()) + 1));
		this.filmService.updateFilm(goods);
		this.getRequest().setAttribute("goods", goods);
		return "users/detail";
	}



}
