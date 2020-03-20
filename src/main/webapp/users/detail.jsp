<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>" />
<title>${title }</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="">首页</a>
			<code> &gt; </code>
			<a href="index/all.action">全部菜品</a>
			<code> &gt; </code>
			<a href="index/cate.action?id=${goods.cateid }">${goods.catename }</a>
			<code> &gt; </code>
			${goods.filmname }
		</div>
	</div>
	<div class="blank"></div>
	<div class="block clearfix">
		<div class="AreaL">
			<div id="category_tree">
				<div class="tit">所有菜品分类</div>
				<dl class="clearfix" style="overflow: hidden;">
					<c:forEach items="${cateList}" var="cate">
						<div class="box1 cate" id="cate">
							<h1 style="border-top: none">
								<a href="index/cate.action?id=${cate.cateid }" class="  f_l">${cate.catename }</a>
							</h1>
						</div>
						<div style="clear: both"></div>
					</c:forEach>
				</dl>
			</div>
			<div class="blank"></div>
			<div class="box" id='history_div'>
				<div class="box_1">
					<h3>
						<span>热门菜品</span>
					</h3>
					<div class="boxCenterList clearfix" id='history_list'>
						<c:forEach items="${hotList}" var="film">
							<ul class="clearfix">
								<li class="goodsimg"><a href="index/detail.action?id=${film.filmid }" target="_blank"><img
										src="${film.image }" alt="${film.filmname }" class="B_blue" /> </a></li>
								<li><a href="index/detail.action?id=${film.filmid }" target="_blank" title="${film.filmname }">${film.filmname }</a>
									<br /> 价格： <font class="f1">￥${film.price }元</font></li>
							</ul>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="blank5"></div>
		</div>
		<div class="AreaR">

			<div id="goodsInfo" class="clearfix">
				<div class="imgInfo">
					<img src="${goods.image}" alt="${goods.filmname }" width="360px;" height="360px" />
					<div class="blank5"></div>
					<div class="blank"></div>
				</div>
				<div class="textInfo">
					<form action="index/addcart.action" method="post" name="ECS_FORMBUY" id="ECS_FORMBUY">
						<h1 class="clearfix">${goods.filmname }</h1>
						<ul class="ul2 clearfix">
							<li class="clearfix" style="width: 100%">
								<dd>
									<strong>价格：</strong><font class="shop" id="ECS_SHOPPRICE">￥${goods.price }元</font>
								</dd>
							</li>
							<li class="clearfix" style="width: 100%">
								<dd>
									<strong>菜品类型：</strong><a href="index/cate.action?id=${goods.cateid }">${goods.catename }</a>
								</dd>
							</li>
							<li class="clearfix" style="width: 100%">
								<dd>
									<strong>点击数：</strong>${goods.hits}
								</dd>
							</li>
							<li class="clearfix" style="width: 100%">
								<dd>
									<strong>销量：</strong>${goods.sellnum}
								</dd>
							</li>
						</ul>
						<ul class="bnt_ul">
							<li class="clearfix">
								<dd>
									<strong>购买数量：</strong> <input name="num" type="text" id="number" value="1" size="4"
										style="border: 1px solid #ccc;" /> <input type="hidden" name="goodsid" value="${goods.filmid }" /> <input
										type="hidden" name="price" value="${goods.price }" />
								</dd>
							</li>
							<li class="padd"><input type="image" src="themes/xecmoban_haier2015/images/goumai2.png" /></li>
						</ul>
					</form>
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div style="padding: 0 0px;">
					<div id="com_b" class="history clearfix">
						<h2>菜品描述</h2>
					</div>
				</div>
				<div class="box_1">
					<div id="com_v" class="  " style="padding: 6px;"></div>
					<div id="com_h">
						<blockquote>${goods.contents}</blockquote>
					</div>
				</div>
			</div>
			<div class="blank"></div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
