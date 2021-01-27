package com.dl.code.controller;

import com.dl.code.service.AdminService;
import com.dl.code.service.impl.AdminServiceImpl;
import com.dl.code.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:46
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
@WebServlet("/adminServlet")
public class AdminServlet extends BaseServlet {

    /**
     * 管理员登录
     * @param request
     * @param response
     * @throws IOException
     */
    public void adminLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //设置请求的编码
        request.setCharacterEncoding("utf-8");
        //设置响应的编码
        response.setContentType("text/html;charset=utf-8");
        //获得管理员登录的时候输入的账号
        String username = request.getParameter("username");
        //获得管理员登录的时候输入的密码
        String password = request.getParameter("password");
        //实例AdminService对象
        AdminService adminService = new AdminServiceImpl();
        //调用方法验证是否允许登录
        boolean flag = adminService.checkLogin(username, password);
        if(flag){
            //允许登录
            //创建一个session对象，将当前管理员姓名存入到session中
            request.getSession().setAttribute("adminUserName",username);
            response.sendRedirect(request.getContextPath()+"/userController?method=getUsersByPage");
        }else {
            //不允许登录
            response.getWriter().append("对不起,账号或密码错误!");
        }
    }

    public void adminLogout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //清除session对象
        HttpSession session = request.getSession(false);
        session.invalidate();
        //重定向登录页面
        response.sendRedirect(request.getContextPath()+"/after/login.jsp");
    }
}
