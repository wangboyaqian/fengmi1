
<%--
  @公司 DL19121630工作室
  @作者: DL代先生
  @日期: 2021/1/19
  @时间: 12:51
  @温馨提示：原创代码，翻版必究！如需代写，微信联系！
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品类型管理页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/after-css/goodsType.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/after-js/goodsType.js"></script>
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
                            <li class="active"><a href="${pageContext.request.contextPath}/goodsTypeServlet?method=getAllTypesByPage">商品类型管理</a></li>
                            <li><a href="${pageContext.request.contextPath}/userController?method=getUsersByPage">用户管理</a></li>
                            <li><a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatus">订单管理</a></li>
                            <li><a href="${pageContext.request.contextPath}/before/login.html">直通锋迷网</a></li>
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
                <h1>商品类型分类管理</h1>
            </div>
            <div id="find-and-add">
                <%-- 添加商品类型分类部分 --%>
                <button type="submit" class="btn btn-info btn-lg" data-toggle="modal" data-target="#add-goodsType">添加商品类型分类</button>
            </div>
            <table id="user-list" class="table table-bordered">
                <tr>
                    <th>商品类型序号</th>
                    <th>商品类型名称</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
                <c:forEach items="${goodsTypeList}" var="goodsType">
                    <tr>
                        <td>${goodsType.id}</td>
                        <td>${goodsType.typename}</td>
                        <%-- 修改商品类型 --%>
                        <td>
<%--                           <a href="">编辑</a>--%>
                            <button id="update-button" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#${goodsType.id}">
                                修改信息
                            </button>
                            <div class="modal fade" id="${goodsType.id}" tabindex="-1" role="dialog" aria-labelledby="update-goodsType-myModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <form action="${pageContext.request.contextPath}/goodsTypeServlet">
                                            <input type="hidden" name="method" value="updateGoodsType">
                                            <input type="hidden" name="id" value="${goodsType.id}">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title">Modal title</h4>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="method" value="addGoodsType">
                                            <div class="input-group input-group-lg">
                                                <span class="input-group-addon">请修改商品类型</span>
                                                <input type="text" class="form-control" name="typename" value="${goodsType.typename}" aria-describedby="sizing-addon1">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                            <button type="submit" class="btn btn-primary">确认</button>
                                        </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td><a href="">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <%-- 分页的按钮部分 --%>
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
                            <a href="${pageContext.request.contextPath}/goodsTypeServlet?method=getAllTypesByPage&&pageNo=${pageNo-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach varStatus="data" begin="1" end="${pageCount}">
                        <c:if test="${data.count == pageNo}">
                            <li class="active"><a>${data.count}</a></li>
                        </c:if>
                        <c:if test="${data.count != pageNo}">
                            <li><a href="${pageContext.request.contextPath}/goodsTypeServlet?method=getAllTypesByPage&&pageNo=${data.count}">${data.count}</a></li>
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
                            <a href="${pageContext.request.contextPath}/goodsTypeServlet?method=getAllTypesByPage&&pageNo=${pageNo+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </article>
    <%-- 添加商品类型部分 --%>
    <div class="modal fade" id="add-goodsType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/goodsTypeServlet">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="method" value="addGoodsType">
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon" id="sizing-addon1">请输入商品类型</span>
                        <input type="text" class="form-control" name="typename" aria-describedby="sizing-addon1">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="submit" class="btn btn-primary">确认</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
