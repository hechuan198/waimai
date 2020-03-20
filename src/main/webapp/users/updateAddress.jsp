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
                        <span>修改地址</span>
                    </h5>
                    <div class="blank"></div>
                    <form  action="index/addressUpdate.action" method="post">
                        <table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">收货姓名</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <input name="receiverName" type="text" size="25"
                                           class="inputBg" id="receiverName" value="${address.receiverName}" />
                                    <input name="id" type="hidden" size="25"
                                           class="inputBg" id="id" value="${address.id}" />
                                </td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">收货固定电话</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <input name="receiverPhone" type="text" size="25"
                                           class="inputBg" id="receiverPhone" value="${address.receiverPhone}" /></td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">收货移动电话</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <input name="receiverMobile" type="text" size="25"
                                           class="inputBg" id="receiverMobile" value="${address.receiverMobile}" /></td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">省份</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <select id="receiverProvince" class="inputBg" name="receiverProvince" onchange="showCity(this)">
                                        <option>${address.receiverProvince}</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">城市</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <select id="receiverCity" class="inputBg" name="receiverCity" onchange="showCountry(this)">
                                        <option>${address.receiverCity}</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">区/县</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <select id="receiverDistrict" class="inputBg" name="receiverDistrict">
                                        <option>${address.receiverDistrict}</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">详细地址</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <input name="receiverAddress" type="text" size="25"
                                           class="inputBg" id="receiverAddress" value="${address.receiverAddress}" /></td>
                            </tr>
                            <tr>
                                <td width="28%" align="right" bgcolor="#FFFFFF">邮编</td>
                                <td width="76%" align="left" bgcolor="#FFFFFF">
                                    <input name="receiverZip" type="text" size="25"
                                           class="inputBg" id="receiverZip" value="${address.receiverZip}" /></td>
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
<script type="text/javascript" src="<%=basePath%>js/userjs/city.js"></script>
<script type="text/javascript" src="<%=basePath%>js/userjs/citymethod.js"></script>
</body>
</html>
