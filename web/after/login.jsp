<%--
  @公司 DL19121630工作室
  @作者: DL代先生
  @日期: 2021/1/18
  @时间: 15:12
  @温馨提示：原创代码，翻版必究！如需代写，微信联系！
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台登录页面</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/after-css/login.css">
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../js/after-js/login.js"></script>
</head>
<body>
    <%--上半部分的颜色--%>
    <div id="top">
        <div id="head-title">锋迷网后台管理系统</div>
    </div>
    <%-- 下半部分的颜色 --%>
    <div id="bottom">
        <div id="footer-title">Copyrignt @2019 版权所有 提供技术支持</div>
    </div>

    <div id="form">
        <form action="${pageContext.request.contextPath}/adminServlet">
            <table>
                <input type="hidden" name="method" value="adminLogin">
                <tr>
                    <td>
                        <input id="username" name="username" class="input form-control" type="text" placeholder="请输入管理员账号">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="password" name="password" class="input form-control" type="password" placeholder="请输入管理员密码">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="button" class="btn btn-info" type="submit" value="登录">
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <%-- 老鹰的头 --%>
    <div id="eagle-head"><img src="../img/after-img/head.png" alt=""></div>
    <%-- 老鹰的左手 --%>
    <div id="eagle-left-hand"><img src="../img/after-img/left_hand.png" alt=""></div>
    <%-- 老鹰的右手 --%>
    <div id="eagle-right-hand"><img src="../img/after-img/right_hand.png" alt=""></div>
</body>
</html>
