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
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js" charset="utf-8"></script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="">首页</a>
			<code> &gt; </code>
			用户中心
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
							<span>个人资料</span>
						</h5>
						<div class="blank"></div>
						<form name="formEdit" action="index/personal.action" method="post" onsubmit="return userEdit()">
							<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">用户名：</td>
									<td width="76%" align="left" bgcolor="#FFFFFF">${sessionScope.users.username }<input type="hidden"
										name="username" style="width: 160px" id="username" value="${sessionScope.users.username }" /> <input
										type="hidden" name="password" style="width: 160px" id="password" value="${sessionScope.users.password }" /> <input
										type="hidden" name="usersid" style="width: 160px" id="usersid" value="${sessionScope.users.usersid }" />
									</td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">邮箱：</td>
									<td width="76%" align="left" bgcolor="#FFFFFF"><input name="email" type="text" size="25"
										class="inputBg" id="email" value="${sessionScope.users.email }" /></td>
								</tr>
								<tr>
									<td width="28%" align="right" bgcolor="#FFFFFF">手机号：</td>
									<td width="76%" align="left" bgcolor="#FFFFFF"><input name="phone" type="text" size="25"
										class="inputBg" id="phone"
										value="${sessionScope.users.phone }" /></td>
								</tr>

								<tr>
									<td colspan="2" align="center" bgcolor="#FFFFFF"><input type="submit" class="bnt_blue_1"
										style="border: none;" value="确认修改" /></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="blank"></div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
