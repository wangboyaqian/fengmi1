package com.dl.code.controller;

import com.dl.code.entity.Order;
import com.dl.code.entity.OrderStatus;
import com.dl.code.entity.User;
import com.dl.code.service.OrderService;
import com.dl.code.service.UserService;
import com.dl.code.service.impl.OrderServiceImpl;
import com.dl.code.service.impl.UserServiceImpl;
import com.dl.code.utils.BaseServlet;
import com.dl.code.utils.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 17:07
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
@WebServlet("/orderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    //定义一个标识：作用：区分 全部的分页 和 查询的分页
    public boolean globalFlag = false;
    /**
     * 分页查询订单信息
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void getAllOrdersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //将标识设置为false。
        globalFlag =false;
        response.setContentType("text/html;charset=utf-8");
        //获取传来的当前页码
        String pageNo = request.getParameter("pageNo");
        if(pageNo == null){
            pageNo = "1";
        }
        //定义每页显示的页码数
        int pageSize = 5;
        //调用方法查询总记录数
        int dataCount = orderService.getDataCount();
        //调用方法 分页查询数据
        List<Order> orderList = orderService.getAllOrdersById(Integer.parseInt(pageNo), pageSize);
        //计算一共分为多少页
        PageUtil pageUtil = new PageUtil(pageSize,dataCount);
        //获取页数
        int pageCount = pageUtil.getPageCount();
        request.setAttribute("pageNo",pageNo);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("orderList",orderList);
        request.setAttribute("globalFlag",globalFlag);
        request.getRequestDispatcher("/after/order.jsp").forward(request,response);
    }

    /**
     * 第一次加载先执行的
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getAllStatus(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        List<OrderStatus> allStatus = orderService.getAllStatus();
        request.setAttribute("allStatus",allStatus);
        request.getRequestDispatcher("/orderServlet?method=getAllOrdersByPage").forward(request,response);
    }

    /**
     * 查询的时候后执行的
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void getAllOrderByCondition(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //将标识设置为true
        globalFlag =true;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String status = request.getParameter("status");
        //从session对象中获取存入的数据
        Map<String,String> map = (Map) request.getSession(false).getAttribute("orderMap");
        //定义中间变量：
        String mapUsername = null;
        String mapStatus = null;
        //声明一个condition 用户拼接模糊查询的条件
        StringBuffer condition = new StringBuffer();
        if(map !=null){
            //取出map中的数据
            mapUsername = map.get("username");
            mapStatus = map.get("status");
        }
        if(map == null){
            //声明一个Map集合
            map = new HashMap<>();
        }
        if(username == null && status == null && mapUsername !=null && mapStatus !=null){
            condition.append("and t_user.username like \"%"+mapUsername+"%\" and t_status.id = "+Integer.parseInt(mapStatus)+"");
        }else if(username == null && mapUsername == null && mapStatus !=null){
            condition.append("and status = "+Integer.parseInt(mapStatus)+"");
        }else if(status == null && mapStatus ==null && mapUsername !=null){
            condition.append("and username like \"%"+mapUsername+"%\"");
        }

        //拼接模糊查询的条加
        if(username !=null&&!"".equals(username)&&status != null&&!"".equals(status)){
            if(!username.equals(mapUsername)){
                mapUsername = username;
            }
            if(!status.equals(mapStatus)){
                mapStatus = status;
            }
            condition.append("and username like \"%"+username+"%\" and t_status.id = "+Integer.parseInt(mapStatus)+"");
        }else if(username !=null&&!"".equals(username)){
            if (!username.equals(mapStatus)){
                mapUsername = username;
                mapStatus = null;
            }
            condition.append("and username like "+username+"");
        }else if(status != null&&!"".equals(status)){
            if(!status.equals(mapStatus)){
                mapStatus = status;
                mapUsername = null;
            }
            condition.append("and t_status.id = "+Integer.parseInt(mapStatus)+"");
        }
        //再次将变量添加到Map集合中
        map.put("username",mapUsername);
        map.put("status",mapStatus);

        //获取当前页码数
        String pageNo = request.getParameter("pageNo");
        if(pageNo == null){
            pageNo = "1";
        }
        //定义每页显示的大小
        int pageSize = 5;
        //获取总记录数
        int dataCount = orderService.getDataCount(condition.toString());
        List<Order> orderList = orderService.getAllOrderByCondition(condition.toString(),Integer.parseInt(pageNo),pageSize);
        PageUtil pageUtil = new PageUtil(pageSize, dataCount);
        int pageCount = pageUtil.getPageCount();
        request.setAttribute("pageNo",pageNo);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("orderList",orderList);
        request.setAttribute("globalFlag",globalFlag);
        //将存有模糊查询条加的map存到session对象中
        request.getSession().setAttribute("orderMap",map);
        request.getRequestDispatcher("/after/order.jsp").forward(request,response);
    }

    /**
     * 查询的时候 先执行的
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getAllStatusByCondition(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        List<OrderStatus> allStatus = orderService.getAllStatus();
        request.setAttribute("allStatus",allStatus);
        request.getRequestDispatcher("/orderServlet?method=getAllOrderByCondition").forward(request,response);
    }

    /**
     * 删除订单信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int i = orderService.deleteOrder(Integer.parseInt(id));
        if(i==1){
            response.sendRedirect(request.getContextPath()+"/orderServlet?method=getAllStatus");
        }else {
            response.sendRedirect(request.getContextPath()+"/after/error.jsp");
        }
    }
}
