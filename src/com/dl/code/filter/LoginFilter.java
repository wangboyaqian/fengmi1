package com.dl.code.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/21 -- 15:09
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect(request.getContextPath()+"/after/login.jsp");
        }
        if(session != null){
            Object name = session.getAttribute("adminUserName");
            if (name == null){
                response.sendRedirect(request.getContextPath()+"/after/login.jsp");
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
