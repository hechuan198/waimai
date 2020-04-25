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
<%--<script type="text/javascript" src="<%=basePath%>js/jquery.min.js" charset="utf-8"></script>--%>
<%--<script type="text/javascript" src="<%=basePath%>js/userjs/users.js" charset="utf-8"></script>--%>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js" charset="utf-8"></script>

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="<%=basePath%>">首页</a>
			<code> &gt; </code>
			用户注册
		</div>
	</div>
	<div class="blank"></div>
	<div class="block">
		<div class="box">
			<div class="box_1">
				<h3>
					<span>用户注册</span>
				</h3>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr valign="top">
						<td bgcolor="#FFFFFF" align="center">
							<form action="index/register.action" method="post" name="myform">
								<table width="60%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
									<tr>
										<td width="28%" align="right" bgcolor="#FFFFFF">用户名：</td>
										<td width="76%" align="left" bgcolor="#FFFFFF"><input type="text" name="username" size="25"
											class="inputBg" id="username" /></td>
									</tr>
									<tr>
										<td width="28%" align="right" bgcolor="#FFFFFF">密码：</td>
										<td width="76%" align="left" bgcolor="#FFFFFF"><input name="password" type="password" size="25"
											class="inputBg" id="password" /></td>
									</tr>
									<tr>
										<td width="28%" align="right" bgcolor="#FFFFFF">确认密码：</td>
										<td width="76%" align="left" bgcolor="#FFFFFF"><input name="rapassword" type="password" size="25"
											class="inputBg" id="rapassword" /></td>
									</tr>
									<tr>
										<td width="28%" align="right" bgcolor="#FFFFFF">电话号码：</td>
										<td width="76%" align="left" bgcolor="#FFFFFF"><input type="text" name="phone" size="25"
																							  class="inputBg" id="phone" /></td>
									</tr>
									<tr>
										<td width="28%" align="right" bgcolor="#FFFFFF">邮箱：</td>
										<td width="76%" align="left" bgcolor="#FFFFFF"><input type="text" name="email" size="25"
																							  class="inputBg" id="email" /></td>
									</tr>
									<tr>
										<td width="28%" align="right" bgcolor="#FFFFFF">找回密码问题：</td>
										<td width="76%" align="left" bgcolor="#FFFFFF"><input type="text" name="question" size="25"
																							  class="inputBg" id="question" /></td>
									</tr>
									<tr>
										<td width="28%" align="right" bgcolor="#FFFFFF">找回密码答案：</td>
										<td width="76%" align="left" bgcolor="#FFFFFF"><input type="text" name="answer" size="25"
																							  class="inputBg" id="answer" /></td>
									</tr>
									<tr>
										<td colspan="2" align="center" bgcolor="#FFFFFF">
											<input type="submit" id="sub" class="bnt_blue_1" style="border: none;" value="确认注册" /></td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="blank5"></div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
