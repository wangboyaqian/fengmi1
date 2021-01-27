package com.dl.code.controller;

import com.dl.code.entity.GoodsType;
import com.dl.code.service.GoodsTypeService;
import com.dl.code.service.impl.GoodsTypeServiceImpl;
import com.dl.code.utils.BaseServlet;
import com.dl.code.utils.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 13:23
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
@WebServlet("/goodsTypeServlet")
public class GoodsTypeServlet extends BaseServlet {
    private GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

    /**
     * 分页查询商品类型
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void getAllTypesByPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        //获得记录的总数
        int dataCount = goodsTypeService.getDataCount();
        //定义每页显示的数量
        int pageSize = 5;
        //获取前端传来的页码数
        String pageNo = request.getParameter("pageNo");
        //如果pageNo没有值，则默认显示第一页
        if(pageNo == null){
            pageNo = "1";
        }
        PageUtil pageUtil = new PageUtil(pageSize, dataCount);
        //获得页数
        int pageCount = pageUtil.getPageCount();
        //查询全部的类型
        List<GoodsType> goodsTypeList = goodsTypeService.getAll(Integer.parseInt(pageNo),pageSize);
        //将当前页码数存入到request域对象中
        request.setAttribute("pageNo",pageNo);
        //将页数存入到request域对象中
        request.setAttribute("pageCount",pageCount);
        //将查询到的结果存入到request域对象中
        request.setAttribute("goodsTypeList",goodsTypeList);
        //重定向到goodsType.jsp页面
        request.getRequestDispatcher("/after/goodsType.jsp").forward(request,response);
    }

    /**
     * 添加商品类型
     * @param request
     * @param response
     * @throws IOException
     */
    public void addGoodsType(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String typename = request.getParameter("typename");
        GoodsType goodsType = new GoodsType();
        goodsType.setTypename(typename);
        int i = goodsTypeService.addGoodsType(goodsType);
        if(i==1){
            response.sendRedirect(request.getContextPath()+"/goodsTypeServlet?method=getAllTypesByPage");
        }else {
            response.sendRedirect(request.getContextPath()+"/after/error.jsp");
        }
    }

    /**
     * 更新商品类型
     * @param request
     * @param response
     * @throws IOException
     */
    public void updateGoodsType(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String typename = request.getParameter("typename");
        String id = request.getParameter("id");
        GoodsType goodsType = new GoodsType();
        goodsType.setTypename(typename);
        goodsType.setId(Integer.parseInt(id));
        int i = goodsTypeService.updateGoodsType(goodsType);
        if(i==1){
            response.sendRedirect(request.getContextPath()+"/goodsTypeServlet?method=getAllTypesByPage");
        }else {
            response.sendRedirect(request.getContextPath()+"/after/error.jsp");
        }
    }

}
