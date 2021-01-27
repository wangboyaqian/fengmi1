package com.dl.code.controller;

import com.dl.code.entity.Goods;
import com.dl.code.entity.GoodsType;
import com.dl.code.service.GoodsService;
import com.dl.code.service.GoodsTypeService;
import com.dl.code.service.impl.GoodsServiceImpl;
import com.dl.code.service.impl.GoodsTypeServiceImpl;
import com.dl.code.utils.BaseServlet;
import com.dl.code.utils.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.*;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 15:08
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
@WebServlet("/goodsServlet")
public class GoodsServlet extends BaseServlet {
    /**
     * 实例化 GoodsService 对象并指向 其子类 GoodsServiceImpl -- 多态
     */
    private GoodsService goodsService = new GoodsServiceImpl();
    /**
     * 实例化 GoodsTypeService 对象并指向 其子类 GoodsTypeServiceImpl
     */
    private GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
    private int i = 1;

    public boolean globalFlag = false;
    /**
     * 分页查询商品
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void getGoodsByPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        globalFlag = false;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String pageNo = request.getParameter("pageNo");
        //设置默认显示第一页
        if(pageNo == null){
            pageNo = "1";
        }
        //定义每页显示的大小
        int pageSize = 5;
        //获取总记录数
        int dataCount = goodsService.getDataCount();
        //获取页数
        PageUtil pageUtil = new PageUtil(pageSize, dataCount);
        int pageCount = pageUtil.getPageCount();
        //获取分页查询的数据
        List<Goods> goodsList = goodsService.getGoodsByPage(Integer.parseInt(pageNo), pageSize);
        List<GoodsType> allGoodsType = goodsService.getAllGoodsType();
        request.setAttribute("pageNo",pageNo);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("goodsList",goodsList);
        request.setAttribute("allGoodsType",allGoodsType);
        //存入分页的标识
        request.setAttribute("globalFlag",globalFlag);
        request.getRequestDispatcher("/after/goods.jsp").forward(request,response);
    }

    /**
     * 获取全部的商品类型
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getAllGoodsType(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        List<GoodsType> allGoodsType = goodsService.getAllGoodsType();
        request.setAttribute("allGoodsType",allGoodsType);
        request.getRequestDispatcher("/after/add_goods.jsp").forward(request,response);
    }

    /**
     * 添加商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void addGoods(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String goodsName = request.getParameter("goodsName");
        String price = request.getParameter("price");
        String deployDate = request.getParameter("deployDate");
        String imgPath = request.getParameter("imgPath");
        String comment = request.getParameter("comment");
        String typeId = request.getParameter("typeId");
        Goods goods = new Goods(goodsName, Double.parseDouble(price), Date.valueOf(deployDate), imgPath, comment, Integer.parseInt(typeId));
        int i = goodsService.addGoods(goods);
        if(i==1){
            response.sendRedirect(request.getContextPath()+"/goodsServlet?method=getGoodsByPage");
        }else {
            response.sendRedirect(request.getContextPath()+"/after/error.jsp");
        }
    }

    /**
     * 修改商品信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void updateGoods(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String goodsName = request.getParameter("goodsName");
        double price = Double.parseDouble(request.getParameter("price"));
        String imgPath = request.getParameter("imgPath");
        Date deployDate = Date.valueOf(request.getParameter("deployDate"));
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String comment = request.getParameter("comment");
        Goods goods = new Goods();
        goods.setId(id);
        goods.setGoodsName(goodsName);
        goods.setPrice(price);
        goods.setImgPath(imgPath);
        goods.setDeployDate(deployDate);
        goods.setTypeId(typeId);
        goods.setComment(comment);
        int i = goodsService.updateGoods(goods);
        if(i == 1){
            response.sendRedirect(request.getContextPath()+"/goodsServlet?method=getGoodsByPage");
        }else {
            response.sendRedirect(request.getContextPath()+"/after/error.jsp");
        }

    }

    /**
     * 逐个删除商品信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteGoods(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        int i = goodsService.deleteGoods(Integer.parseInt(id));
        if(i == 1){
            response.sendRedirect(request.getContextPath()+"/goodsServlet?method=getGoodsByPage");
        }else {
            response.sendRedirect(request.getContextPath()+"/after/error.jsp");
        }
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws IOException
     */
    public void batchDelete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        //获取前台传来的内容，但是注意，这里是将前台的数组当作一个字符串
        // 存入了 checkInputs 数组中，因此此时的数组长度是1
        String[] checkInputs = request.getParameterValues("array");
        //将数组 转换成字符串
        String string = Arrays.toString(checkInputs);
        //进行字符串切割，掐头去尾 目的是去掉 开头的 '[' 和结尾 ']'
        String substring = string.substring(1,string.lastIndexOf("]"));
        //生成新的字符数组 按照 ',' 进行分割
        String[] array = substring.split(",");
        //将字符数组转换成int 类型的数组
        int[] intArray = Arrays.asList(array).stream().mapToInt(Integer::parseInt).toArray();
        //创建一个List集合
        List<Integer> list = new ArrayList<>();
        //将数组的元素 赋值给 集合
        for (int i : intArray) {
            list.add(i);
        }
        //掉用方法 完成批量删除
        int i = goodsService.batchDelete(list);
        if (i>0){
            //删除成功
            response.sendRedirect(request.getContextPath()+"/goodsServlet?method=getGoodsByPage");
        }else {
            //删除失败
            response.sendRedirect(request.getContextPath()+"/after/error.jsp");
        }
    }


    /**
     * 代条件的分页查询
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void getAllGoodsByCondition(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        /*
            参考： condition = "and t_goods.goodsName LIKE '%小米%' AND t_goods.deployDate = '2019-04-29'"
        */
        globalFlag = true;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String goodsName = request.getParameter("goodsName");
        String deployDate = request.getParameter("deployDate");
        //从session获取第一次查询的时候的条件
        Map<String,String> map = (Map) request.getSession(false).getAttribute("goodsMap");
                //定义中间变量
        String mapGoodsName = null;
        String mapDeployDate = null;
        //声明一个StringBuffer 用来存储条件
        StringBuffer condition = new StringBuffer();
        //判断map是否为null，
        if (map != null){
            // 如果不是null，则表示用户查询过后点击了下一页
            //取出map中 第一次查询的条件
            mapGoodsName = map.get("goodsName");
            mapDeployDate = map.get("deployDate");

        }
        if (map == null){
            //如果为null，则表明这是第一次查询。
            //实例化一个map集合，用来存储查询的条件
            map = new HashMap<>();
        }

        //准备给condition赋值 赋值之前首先判断以下条件 -- 相同条加下，不是第一次查询的时候执行
        if(goodsName == null && deployDate == null && mapGoodsName != null && mapDeployDate != null){
            //这个条件表明：本次的查询 不是第一次查询 而且 第一次的查询条件 是两个 goodsNames 和 deployDate
            //给condition赋值，这里需要注意，因为map集合已经存储了第一次查询的条加，所以这里需要用map集合中的条件
            condition.append("and t_goods.goodsName like \"%"+mapGoodsName+"%\" and t_goods.deployDate = \""+mapDeployDate+"\"");
        }else if(goodsName == null && mapGoodsName == null && mapDeployDate != null){
            //这个条件表明：本次的查询 不是第一次查询 而且 第一次的查询条件 是一个 deployDate
            //给condition赋值，这里需要注意，因为map集合已经存储了第一次查询的条加，所以这里需要用map集合中的条件
            condition.append("and t_goods.deployDate = \""+mapDeployDate+"\"");
        }else if (deployDate == null && mapDeployDate == null && mapGoodsName != null){
            //这个条件表明：本次的查询 不是第一次查询 而且 第一次的查询条件 是一个 goodsNames
            //给condition赋值，这里需要注意，因为map集合已经存储了第一次查询的条加，所以这里需要用map集合中的条件
            condition.append("and t_goods.goodsName like \"%"+mapGoodsName+"%\"");
        }

        //同一个条件下 的 第一次查询的时候执行
        if(goodsName != null && !"".equals(goodsName) && deployDate != null && !"".equals(deployDate)){
            //条件为两个 deployDate 和 goodsNames

            //更新map里面的条件
            if(!goodsName.equals(mapGoodsName)){
                mapGoodsName = goodsName;
            }
            if (!deployDate.equals(mapDeployDate)){
                mapDeployDate = deployDate;
            }
            //给condition赋值，这里需要注意，因为这是同一条加下的第一次查询 为了便于理解
            //这里没有直接用map集合里面的值，而是直接用 获得到的值
            condition.append("and t_goods.goodsName like \"%"+goodsName+"%\" and t_goods.deployDate = \""+deployDate+"\"");
        }else if(goodsName !=null&&!"".equals(goodsName)){
            //条件为一个
            //更新map里面的条件
            if(!goodsName.equals(mapGoodsName)){

                mapGoodsName = goodsName;
                //清除之前map中的另一个条件

                mapDeployDate = null;
            }
            condition.append("and t_goods.goodsName like \"%"+goodsName+"%\"");
        }else if(deployDate != null && !"".equals(deployDate)){
            if (!deployDate.equals(mapDeployDate)){
                mapDeployDate = deployDate;
                //清除之前map中的另一个条件
                mapGoodsName = null;
            }
            condition.append("and t_goods.deployDate = \""+deployDate+"\"");
        }

        //将新的条件存入到map集合中
        map.put("goodsName",mapGoodsName);
        map.put("deployDate",mapDeployDate);

        //获取前端传来的当前页码数
        String pageNo = request.getParameter("pageNo");
        //如果为null，则默认显示第一页
        if(pageNo == null){
            pageNo = "1";
        }
        //定义每页显示的数目
        int pageSize = 5;
        //获取当前条件下的记录总数
        int dataCount = goodsService.getDataCountInCondition(condition.toString());
        //用工具类PageUtil计算需要分多少页数
        PageUtil pageUtil = new PageUtil(pageSize, dataCount);
        //获取页数
        int pageCount = pageUtil.getPageCount();
        //查询当前条件下的所有商品信息
        List<Goods> goodsList = goodsService.getAllGoodsByCondition(condition.toString(), Integer.parseInt(pageNo), pageSize);
        //将条件存入到request域对象中
        //存入当前页码数
        request.setAttribute("pageNo",pageNo);
        //存入页数
        request.setAttribute("pageCount",pageCount);
        //存入商品信息
        request.setAttribute("goodsList",goodsList);
        //存入查询条件
        request.getSession().setAttribute("goodsMap",map);
        //存入分页的标识
        request.setAttribute("globalFlag",globalFlag);
        //转发
        request.getRequestDispatcher("/after/goods.jsp").forward(request,response);
    }


}
