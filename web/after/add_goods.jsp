<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  @公司 DL19121630工作室
  @作者: DL代先生
  @日期: 2021/1/19
  @时间: 20:18
  @温馨提示：原创代码，翻版必究！如需代写，微信联系！
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/after-css/add_goods.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/after-js/add_goods.js"></script>
</head>
<body>
    <div class="container">
        <div class="page-header" style="text-align: center;height: 50px;line-height: 50px">
            <h1>添加商品</h1>
        </div>
        <form action="${pageContext.request.contextPath}/goodsServlet">
            <input type="hidden" name="method" value="addGoods">
            <table>
                <tr>
                    <td>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="goods-name">商品名称</span>
                            <input type="text" name="goodsName" class="form-control" placeholder="" aria-describedby="sizing-addon1">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="goods-price">商品价格</span>
                            <input type="text" name="price" class="form-control" aria-describedby="sizing-addon1">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="goods-imgPath">商品照片</span>
                            <input type="text" name="imgPath" class="form-control" aria-describedby="sizing-addon1">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="goods-type">商品类型</span>
<%--                            <input type="text" class="form-control" aria-describedby="sizing-addon1">--%>
                            <select name="typeId" class="form-control">
                                <option value="">请选择商品类型</option>
                                <c:forEach items="${allGoodsType}" var="goodsType">
                                    <option value="${goodsType.id}">${goodsType.typename}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="goods-date">上架时间</span>
                            <input type="date" name="deployDate" class="form-control" aria-describedby="sizing-addon1">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="input-group input-group-lg">
                            <span class="input-group-addon" id="goods-comment">商品描述</span>
                            <textarea name="comment" style="resize: none;width:300px;height: 200px;" rows="10" type="text" class="form-control" aria-describedby="sizing-addon1"></textarea>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><button type="submit" class="btn btn-info add-goods-button">确认添加</button></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
