package com.dl.code.service;

import com.dl.code.entity.User;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:29
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface UserService {
    //分页获取用户的信息
    public List<User> getUserByPage(int pageNo,int pageSize);

    //获取用户的总记录数
    public int getDataCount();

    //模糊查询的分页
    public List<User> getAllLikeUsers(String condition,int pageNo,int pageSize);

    //模糊查询的总记录数
    public int getAllLikeUsersCount(String condition);

    //删除用户
    public int deleteUser(int id);

    //重置密码
    public int resetPassword(int id);

    //根据姓名查询用户
    public List<User> getAllUserByUsername(String username);

    //校验登录
    public int checkLogin(String username,String password);

    //注册用户
    public int register(User user);
}
