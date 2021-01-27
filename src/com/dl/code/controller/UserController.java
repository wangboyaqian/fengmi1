package com.dl.code.controller;

import com.dl.code.entity.User;
import com.dl.code.service.UserService;
import com.dl.code.service.impl.UserServiceImpl;
import com.dl.code.utils.BaseServlet;
import com.dl.code.utils.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 19:11
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
@WebServlet("/userController")
public class UserController extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    //定义一个标识：作用：区分 全部的分页 和 模糊查询的分页
    public boolean globalFlag = false;

    /**
     * 分页查询  -- 查询结果：全部的用户
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void getUsersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //将标识设置为false。
        globalFlag =false;
        //设置请求的编码
        request.setCharacterEncoding("utf-8");
        //设置响应的编码
        response.setContentType("text/html;charset=utf-8");
        //获取总记录数
        int dataCount = userService.getDataCount();
        //定义每页显示的数量
        int pageSize = 5;
        //用PageUtil计算总页数
        PageUtil pageUtil = new PageUtil(pageSize,dataCount);
        //获得总页数
        int pageCount = pageUtil.getPageCount();
        //从前端获取当前的页码数
        String pageNo = request.getParameter("pageNo");
        //如果pageNo为null,则默认显示第一页
        if(pageNo==null){
            pageNo="1";
        }
        //调用方法分页查询用户
        List<User> users = userService.getUserByPage(Integer.parseInt(pageNo), pageSize);
        //将查询道的结果存入到request域对象中
        request.setAttribute("users",users);
        //将当前页码数存入到request域对象中
        request.setAttribute("pageNo",pageNo);
        //将总页数存入到request对象中
        request.setAttribute("pageCount",pageCount);
        //将标识存到request域对象中
        request.setAttribute("globalFlag",globalFlag);
        //执行转发操作
        request.getRequestDispatcher("/after/user.jsp").forward(request,response);
    }

    /**
     * 模糊分页查询 -- 查询结果：部分的用户
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void getAllLikeUsers(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //将标识设置为true
        globalFlag =true;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        //从session对象中获取存入的数据
        Map<String,String> map = (Map) request.getSession(false).getAttribute("map");

        //定义中间变量：
        String mapUsername = null;
        String mapSex = null;
        //声明一个condition 用户拼接模糊查询的条件
        StringBuffer condition = new StringBuffer();
        if(map !=null){
            //取出map中的数据
            mapUsername = map.get("username");
            mapSex = map.get("sex");
        }
        if(map == null){
            //声明一个Map集合
            map = new HashMap<>();
        }
        if(username == null && sex == null && mapUsername !=null && mapSex !=null){
            condition.append("and username like \"%"+mapUsername+"%\" and sex like \"%"+mapSex+"%\"");
        }else if(username == null && mapUsername == null && mapSex !=null){
            condition.append("and sex like \"%"+mapSex+"%\"");
        }else if(sex == null && mapSex ==null && mapUsername !=null){
            condition.append("and username like \"%"+mapUsername+"%\"");
        }
        //拼接模糊查询的条加
        if(username !=null&&!"".equals(username)&&sex != null&&!"".equals(sex)){
            if(!username.equals(mapUsername)){
                mapUsername = username;
            }
            if(!sex.equals(mapSex)){
                mapSex = sex;
            }
            condition.append("and username like \"%"+username+"%\" and sex like \"%"+sex+"%\"");
        }else if(username !=null&&!"".equals(username)){
            if (!username.equals(mapSex)){
                mapUsername = username;
                mapSex = null;
            }
            condition.append("and username like \"%"+username+"%\"");
        }else if(sex != null&&!"".equals(sex)){
            if(!sex.equals(mapSex)){
                mapSex = sex;
                mapUsername = null;
            }
            condition.append("and sex like \"%"+sex+"%\"");
        }
        //再次将变量添加到Map集合中
        map.put("username",mapUsername);
        map.put("sex",mapSex);
        //获取总记录数
        int dataCount = userService.getAllLikeUsersCount(condition.toString());
        //定义每页显示的数量
        int pageSize = 5;
        //用PageUtil计算总页数
        PageUtil pageUtil = new PageUtil(pageSize,dataCount);
        //获得总页数
        int pageCount = pageUtil.getPageCount();
        //从前端获取当前的页码数
        String pageNo = request.getParameter("pageNo");
        //如果pageNo为null,则默认显示第一页
        if(pageNo==null){
            pageNo="1";
        }
        List<User> allLikeUsers = userService.getAllLikeUsers(condition.toString(), Integer.parseInt(pageNo), pageSize);
        //将查询道的结果存入到request域对象中
        request.setAttribute("users",allLikeUsers);
        //将当前页码数存入到request域对象中
        request.setAttribute("pageNo",pageNo);
        //将总页数存入到request对象中
        request.setAttribute("pageCount",pageCount);
        //将标志存到域对象中
        request.setAttribute("globalFlag",globalFlag);
        //将存有模糊查询条加的map存到session对象中
        request.getSession().setAttribute("map",map);
        //执行转发操作
        request.getRequestDispatcher("/after/user.jsp").forward(request,response);
    }

    /**
     * 删除指定的用户  根据ID删除
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取需要被删除用户的id
        String id = request.getParameter("id");
        //调用方法删除用户
        int i = userService.deleteUser(Integer.parseInt(id));
        if (i==1){
            //删除成功
            response.sendRedirect(request.getContextPath()+"/userController?method=getUsersByPage");
        }else {
            //删除失败
            response.sendRedirect(request.getContextPath() + "/after/error.jsp");
        }
    }

    /**
     * 重置用户的密码
     * @param request
     * @param response
     * @throws IOException
     */
    public void resetPassword(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取当前需要重置用户密码的ID
        String id = request.getParameter("id");
        //调用方法重置密码  根据id重置
        int i = userService.resetPassword(Integer.parseInt(id));
        if (i == 1){
            //重置密码成功
            response.sendRedirect(request.getContextPath()+"/userController?method=getUsersByPage");
        }else {
            //重置密码失败
            response.sendRedirect(request.getContextPath() + "/after/error.jsp");
        }
    }


    /**
     * 校验用户登录
     * @param request
     * @param response
     * @throws IOException
     */
    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int i = userService.checkLogin(username, password);
        if (i>0){
            request.getSession().setAttribute("user",username);
            response.sendRedirect(request.getContextPath()+"/before/index.html");
        }else {
            response.sendRedirect(request.getContextPath()+"/before/error.html");
        }
    }

    /**
     * 用户注册功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void register(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String mail = request.getParameter("mail");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setPhone(phone);
        user.setEmail(mail);
        int register = userService.register(user);
        if (register == 1){
            response.sendRedirect(request.getContextPath()+"/before/success.html");
        }else {
            response.sendRedirect(request.getContextPath()+"/before/error.html");
        }
    }
}
