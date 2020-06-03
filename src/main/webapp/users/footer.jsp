<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<div class="all-help bgc-f2">
	<div class="container clearfix" style="overflow: hidden;">
	</div>
</div>
<div class="all-footer">
	<div class="container clearfix">
		<p class="footer-links">
		</p>
		<p class="p-icp">
		</p>
		<p class="p-copyright">
			&copy; 2020 ${title }
		</p>
		<p class="p-icp">
			<a href="http://localhost:63343/waimai/Backstage-service/login.html" target="_blank">系统管理员入口</a>
			<a href="http://localhost:63343/waimai/Backstage/login.html" target="_blank">订餐管理员入口</a>
			<%--<a href="http://39.107.249.15:8800/waimai/Backstage-service/login.html" target="_blank">系统管理员入口</a>--%>
			<%--<a href="http://39.107.249.15:8800/waimai/Backstage/login.html" target="_blank">订餐管理员入口</a>--%>
		</p>
	</div>
</div>
