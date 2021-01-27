<%--
  @公司 DL19121630工作室
  @作者: DL代先生
  @日期: 2021/1/18
  @时间: 16:49
  @温馨提示：原创代码，翻版必究！如需代写，微信联系！
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/after-css/user.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/after-js/user.js"></script>
</head>
<body>
    <%-- 头部导航部分 --%>
    <header>
        <nav class="navbar navbar-fixed-top navbar-inverse">
            <div class="container">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">锋迷网管理系统</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/goodsServlet?method=getGoodsByPage">商品管理</a></li>
                            <li><a href="${pageContext.request.contextPath}/goodsTypeServlet?method=getAllTypesByPage">商品类型管理</a></li>
                            <li class="active"><a href="${pageContext.request.contextPath}/userController?method=getUsersByPage">用户管理</a></li>
                            <li><a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatus">订单管理</a></li>
                            <li><a href="#">直通锋迷网</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">${sessionScope.get("adminUserName")}</a></li>
                            <li><a href="${pageContext.request.contextPath}/adminServlet?method=adminLogout">退出登录</a></li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </div>
        </nav>
    </header>
    <article>
        <div class="container">
            <div class="page-header" style="text-align: center;height: 50px;line-height: 50px">
                <h1>用户管理</h1>
            </div>
            <div id="find-and-add">
                <%-- 查询功能部分 --%>
                <form class="form-inline" id="find-user" action="${pageContext.request.contextPath}/userController">
                    <input type="hidden" name="method" value="getAllLikeUsers">
                    <div class="form-group">
                        <label for="exampleInputName2">用户姓名:</label>
                        <input type="text" name="username" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
                    </div>
                    <div class="form-group">
                        <label>男</label>
                        <input type="checkbox" value="男" name="sex">
                        <label>女</label>
                        <input type="checkbox" value="女" name="sex">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>
                <%-- 添加功能部分 --%>
                <button type="button" class="btn btn-info add-button">添加用户</button>
            </div>
            <table id="user-list" class="table table-bordered">
                <tr>
                    <th>用户序号</th>
                    <th>用户姓名</th>
                    <th>用户性别</th>
                    <th>手机号码</th>
                    <th>用户邮箱</th>
                    <th>删除用户</th>
                    <th>修改信息</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.sex}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td><a href="${pageContext.request.contextPath}/userController?method=resetPassword&&id=${user.id}">重置密码</a></td>
                        <td><a href="${pageContext.request.contextPath}/userController?method=deleteUser&&id=${user.id}">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <%-- 在这里 判断 显示哪一个分页
            如果globalFlag 为false，
            则显示全部的分页
            如果是true则显示模糊查询的分页 --%>
            <c:if test="${globalFlag == false}">
            <nav aria-label="Page navigation" style="text-align: center">
                <ul class="pagination pagination-lg">
                    <c:if test="${pageNo == 1}">
                        <li class="disabled">
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${pageNo != 1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/userController?method=getUsersByPage&&pageNo=${pageNo-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach varStatus="data" begin="1" end="${pageCount}">
                        <c:if test="${data.count == pageNo}">
                            <li class="active"><a>${data.count}</a></li>
                        </c:if>
                        <c:if test="${data.count != pageNo}">
                            <li><a href="${pageContext.request.contextPath}/userController?method=getUsersByPage&&pageNo=${data.count}">${data.count}</a></li>
                        </c:if>
                    </c:forEach>
                   <c:if test="${pageNo == pageCount}">
                       <li class="disabled">
                           <a href="#" aria-label="Next">
                               <span aria-hidden="true">&raquo;</span>
                           </a>
                       </li>
                   </c:if>
                    <c:if test="${pageNo != pageCount}">
                        <li>
                            <a href="${pageContext.request.contextPath}/userController?method=getUsersByPage&&pageNo=${pageNo+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
            </c:if>
            <c:if test="${globalFlag == true}">
                <nav aria-label="Page navigation" style="text-align: center">
                    <ul class="pagination pagination-lg">
                        <c:if test="${pageNo == 1}">
                            <li class="disabled">
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${pageNo != 1}">
                            <li>
                                <a href="${pageContext.request.contextPath}/userController?method=getAllLikeUsers&&pageNo=${pageNo-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach varStatus="data" begin="1" end="${pageCount}">
                            <c:if test="${data.count == pageNo}">
                                <li class="active"><a>${data.count}</a></li>
                            </c:if>
                            <c:if test="${data.count != pageNo}">
                                <li><a href="${pageContext.request.contextPath}/userController?method=getAllLikeUsers&&pageNo=${data.count}">${data.count}</a></li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${pageNo == pageCount}">
                            <li class="disabled">
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${pageNo != pageCount}">
                            <li>
                                <a href="${pageContext.request.contextPath}/userController?method=getAllLikeUsers&&pageNo=${pageNo+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
        </div>
    </article>
</body>
</html>
