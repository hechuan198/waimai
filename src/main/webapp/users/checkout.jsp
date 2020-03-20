<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<title>${title }</title>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>js/ajax.js" charset="utf-8"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="<%=basePath%>">首页</a>
			<code> &gt; </code>
			购物流程
		</div>
	</div>
	<div class="blank"></div>
	<div class="block table">
		<form action="index/checkout.action" method="post" name="theForm" id="theForm" onsubmit="return checkConsignee(this)">
			<div class="flowBox">
				<h6>
					<span>选择地址</span>
				</h6>
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
								<a href="index/checkout.action?id=${address.id}">选择</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
