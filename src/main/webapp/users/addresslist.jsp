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
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="">首页</a>
			<code> &gt; </code>
			我的地址
		</div>
	</div>
	<div class="blank"></div>

	<div class="blank"></div>
	<div class="block clearfix">

		<div class="AreaL">
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox">
						<jsp:include page="usermenu.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<div class="AreaR">
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox boxCenterList clearfix" style="_height: 1%;">
						<h5>
							<span>我的地址</span>
						</h5>
						<div><button type="button" class="bnt_blue_1"><a href="index/preAddAddress.action" >添加地址</a> </button></div>
						<div class="blank"></div>
						<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
							<tr>
								<th align="center" bgcolor="#ffffff">收货姓名</th>
								<th align="center" bgcolor="#ffffff">收货固定电话</th>
								<th align="center" bgcolor="#ffffff">收货移动电话</th>
								<th align="center" bgcolor="#ffffff">省份</th>
								<th align="center" bgcolor="#ffffff">城市</th>
								<th align="center" bgcolor="#ffffff">区/县</th>
								<th align="center" bgcolor="#ffffff">详细地址</th>
								<th align="center" bgcolor="#ffffff">邮编</th>
								<th align="center" bgcolor="#ffffff">操作</th>
							</tr>
							<c:forEach items="${addressList}" var="address">
								<tr>
									<td align="center" bgcolor="#ffffff">${address.receiverName}</td>
									<td align="center" bgcolor="#ffffff">${address.receiverPhone}</td>
									<td align="center" bgcolor="#ffffff">${address.receiverMobile}</td>
									<td align="center" bgcolor="#ffffff">${address.receiverProvince}</td>
									<td align="center" bgcolor="#ffffff">${address.receiverCity}</td>
									<td align="center" bgcolor="#ffffff">${address.receiverDistrict}</td>
									<td align="center" bgcolor="#ffffff">${address.receiverAddress}</td>
									<td align="center" bgcolor="#ffffff">${address.receiverZip}</td>
									<td align="center" bgcolor="#ffffff">
										<a href="index/preAddressUpdate.action?id=${address.id}">修改</a>
										<a href="index/addressDelete.action?id=${address.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="blank5"></div>
						<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
							<tr>
								<td align="center" bgcolor="#ffffff">${html}</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="blank"></div>



	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
