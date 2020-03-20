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
    <script type="text/javascript">
        function checkLogin() {
            var username = document.myform.username.value;
            var password = document.myform.password.value;
            if (username == '') {
                alert('请输入用户名');
                document.myform.username.focus();
                return false;
            }
            if (password == '') {
                alert('请输入密码');
                document.myform.password.focus();
                return false;
            }
        }
    </script>
</head>
<%
    String message = (String) session.getAttribute("message");
    if (message == null) {
        message = "";
    }
    if (!message.trim().equals("")) {
        out.println("<script language='javascript'>");
        out.println("alert('" + message + "');");
        out.println("</script>");
    }
    session.removeAttribute("message");
%>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="block box">
    <div class="blank"></div>
    <div id="ur_here">
        当前位置:
        <a href="<%=basePath%>">首页</a>
        <code>
            &gt;
        </code>
        忘记密码
    </div>
</div>
<div class="blank"></div>
<div class="block">
    <div class="box">
        <div class="box_1">
            <h3>
                <span>忘记密码</span>
            </h3>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr valign="top">
                    <td bgcolor="#FFFFFF" align="center">
                        <form action="index/forgetQuestion.action" method="post" name="myform" onsubmit="return checkLogin()">
                            <table width="60%" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
                                <tr>
                                    <td width="28%" align="left" bgcolor="#FFFFFF">
                                        请输入问题答案：
                                    </td>
                                </tr>
                                <tr>
                                    <td width="76%" align="center" bgcolor="#FFFFFF">
                                        ${user.question}
                                    </td>
                                </tr>
                                <tr>
                                    <td width="76%" align="center" bgcolor="#FFFFFF">
                                        <input type="hidden" name="username" size="25" class="inputBg" id="username" valve="${user.username} " />
                                        <input type="text" name="answer" size="25" class="inputBg" id="answer" />

                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center" bgcolor="#FFFFFF">
                                        <input type="submit" class="bnt_blue_1" style="border: none;" value="提交" />
                                    </td>
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
