<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  @公司 DL19121630工作室
  @作者: DL代先生
  @日期: 2021/1/19
  @时间: 16:29
  @温馨提示：原创代码，翻版必究！如需代写，微信联系！
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单管理页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/after-css/order.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/after-js/order.js"></script>
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
                            <li><a href="${pageContext.request.contextPath}/userController?method=getUsersByPage">用户管理</a></li>
                            <li class="active"><a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatus">订单管理</a></li>
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
                <h1>订单管理</h1>
            </div>
            <div id="find-and-add">
                <%-- 查询功能部分 --%>
                <form class="form-inline" action="${pageContext.request.contextPath}/orderServlet">
                    <input type="hidden" name="method" value="getAllStatusByCondition">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">用户名称</div>
                            <input type="text" name="username" class="form-control">
                        </div>
                        <div class="input-group">
                            <div class="input-group-addon">订单状态</div>
                            <select name="status" id="" class="form-control">
                                <option disabled="disabled" value="">请选择订单状态</option>
                               <c:forEach items="${allStatus}" var="status">
                                   <option value="${status.id}">${status.order_status}</option>
                               </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-info">查询</button>
                </form>
            </div>
            <table id="user-list" class="table table-bordered">
                <tr>
                    <th>订单序号</th>
                    <th>订单编号</th>
                    <th>总金额</th>
                    <th>订单状态</th>
                    <th>下单时间</th>
                    <th>用户名</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
                <c:forEach items="${orderList}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.orderCode}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.createDate}</td>
                        <td>${order.uname}</td>
                        <td>${order.statusName}</td>
                        <td><a href="">编辑</a></td>
                        <td><a href="${pageContext.request.contextPath}/orderServlet?method=deleteOrder&&id=${order.id}">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
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
                              <a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatus&&pageNo=${pageNo-1}" aria-label="Previous">
                                  <span aria-hidden="true">&laquo;</span>
                              </a>
                          </li>
                      </c:if>

                      <c:forEach varStatus="data" begin="1" end="${pageCount}">
                          <c:if test="${data.count == pageNo}">
                              <li class="active"><a>${data.count}</a></li>
                          </c:if>
                          <c:if test="${data.count != pageNo}">
                              <li><a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatus&&pageNo=${data.count}">${data.count}</a></li>
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
                              <a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatus&&pageNo=${pageNo+1}" aria-label="Next">
                                  <span aria-hidden="true">&raquo;</span>
                              </a>
                          </li>
                      </c:if>
                  </ul>
              </nav>
          </c:if>
            <%--            --%>
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
                                <a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatusByCondition&&pageNo=${pageNo-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach varStatus="data" begin="1" end="${pageCount}">
                            <c:if test="${data.count == pageNo}">
                                <li class="active"><a>${data.count}</a></li>
                            </c:if>
                            <c:if test="${data.count != pageNo}">
                                <li><a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatusByCondition&&pageNo=${data.count}">${data.count}</a></li>
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
                                <a href="${pageContext.request.contextPath}/orderServlet?method=getAllStatusByCondition&&pageNo=${pageNo+1}" aria-label="Next">
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
