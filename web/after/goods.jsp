<%--
  @公司 DL19121630工作室
  @作者: DL代先生
  @日期: 2021/1/19
  @时间: 12:36
  @温馨提示：原创代码，翻版必究！如需代写，微信联系！
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品管理页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/after-css/goods.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/after-js/goods.js"></script>
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
                            <li class="active"><a href="${pageContext.request.contextPath}/goodsServlet?method=getGoodsByPage">商品管理</a></li>
                            <li><a href="${pageContext.request.contextPath}/goodsTypeServlet?method=getAllTypesByPage">商品类型管理</a></li>
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
                <h1>商品管理</h1>
            </div>
            <div id="find-and-add">
                <div>
                    <button type="button" class="btn btn-info add-button">添加商品</button>
                    <button type="button" class="btn btn-info delete-button">批量删除</button>
                    <button type="button" class="btn btn-info delete-button">商品类型</button>
                </div>
                <div>
                    <form class="form-inline" action="${pageContext.request.contextPath}/goodsServlet">
                        <input type="hidden" name="method" value="getAllGoodsByCondition">
                        <div class="form-group">
                            <label class="sr-only" for="exampleInputEmail3">商品名称</label>
                            <input type="text" name="goodsName" class="form-control" id="exampleInputEmail3" placeholder="请输入商品名称">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="exampleInputPassword3">上架日期</label>
                            <input type="date" name="deployDate" class="form-control" id="exampleInputPassword3" placeholder="请选择上架日期">
                        </div>
                        <button type="submit" class="btn btn-info">查询</button>
                    </form>
                </div>
            </div>
            <table id="user-list" class="table table-bordered">
                <tr>
                    <th>全选</th>
                    <th>序号</th>
                    <th>商品名称</th>
                    <th>商品价格</th>
                    <th>商品图片</th>
                    <th>上架时间</th>
                    <th>商品类型</th>
                    <th>商品描述</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
                <c:forEach items="${goodsList}" var="goods">
                    <tr>
                        <td><input class="checkInput" name="checkInput" value="${goods.id}" type="checkbox"></td>
                        <td>${goods.id}</td>
                        <td>${goods.goodsName}</td>
                        <td>${goods.price}</td>
                        <td><img style="width: 100px;height: 100px" src="${goods.imgPath}" alt=""></td>
                        <td>${goods.deployDate}</td>
                        <td>${goods.typeName}</td>
                        <td>${goods.comment}</td>
                        <td>
<%--                            <a href="">编辑</a>--%>
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#${goods.id}">修改商品信息</button>
                            <%--更新商品信息--%>
                            <div class="modal fade" id="${goods.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <form action="${pageContext.request.contextPath}/goodsServlet">
                                        <input type="hidden" name="method" value="updateGoods">
                                        <input type="hidden" name="id" value="${goods.id}">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">修改商品信息</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="input-group">
                                            <span class="input-group-addon">商品名称</span>
                                            <input type="text" name="goodsName" value="${goods.goodsName}" class="form-control" aria-describedby="basic-addon1">
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-addon">商品价格</span>
                                            <input type="text" name="price" value="${goods.price}" class="form-control" aria-describedby="basic-addon1">
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-addon">商品图片</span>
                                            <input type="text" name="imgPath" value="${goods.imgPath}" class="form-control" aria-describedby="basic-addon1">
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-addon">上架时间</span>
                                            <input type="text" name="deployDate" value="${goods.deployDate}" class="form-control" aria-describedby="basic-addon1">
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-addon">商品类型</span>
                                            <select name="typeId" class="form-control">
                                                <c:forEach items="${allGoodsType}" var="goodsType">
                                                    <c:if test="${goods.typeId==goodsType.id}">
                                                        <option selected="selected" value="${goodsType.id}">${goodsType.typename}</option>
                                                    </c:if>
                                                    <c:if test="${goods.typeId!=goodsType.id}">
                                                        <option value="${goodsType.id}">${goodsType.typename}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="input-group">
                                            <span class="input-group-addon">商品描述</span>
                                            <input type="text" name="comment" value="${goods.comment}" class="form-control" aria-describedby="basic-addon1">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <button type="submit" class="btn btn-primary">确定</button>
                                    </div>
                                    </form>
                                </div>
                            </div>
                            </div>
                        </td>
                        <td><a href="${pageContext.request.contextPath}/goodsServlet?method=deleteGoods&id=${goods.id}">删除</a></td>
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
                               <a href="${pageContext.request.contextPath}/goodsServlet?method=getGoodsByPage&&pageNo=${pageNo-1}" aria-label="Previous">
                                   <span aria-hidden="true">&laquo;</span>
                               </a>
                           </li>
                       </c:if>

                       <c:forEach varStatus="data" begin="1" end="${pageCount}">
                           <c:if test="${data.count == pageNo}">
                               <li class="active"><a>${data.count}</a></li>
                           </c:if>
                           <c:if test="${data.count != pageNo}">
                               <li><a href="${pageContext.request.contextPath}/goodsServlet?method=getGoodsByPage&&pageNo=${data.count}">${data.count}</a></li>
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
                               <a href="${pageContext.request.contextPath}/goodsServlet?method=getGoodsByPage&&pageNo=${pageNo+1}" aria-label="Next">
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
                                <a href="${pageContext.request.contextPath}/goodsServlet?method=getAllGoodsByCondition&&pageNo=${pageNo-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach varStatus="data" begin="1" end="${pageCount}">
                            <c:if test="${data.count == pageNo}">
                                <li class="active"><a>${data.count}</a></li>
                            </c:if>
                            <c:if test="${data.count != pageNo}">
                                <li><a href="${pageContext.request.contextPath}/goodsServlet?method=getAllGoodsByCondition&&pageNo=${data.count}">${data.count}</a></li>
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
                                <a href="${pageContext.request.contextPath}/goodsServlet?method=getAllGoodsByCondition&&pageNo=${pageNo+1}" aria-label="Next">
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
