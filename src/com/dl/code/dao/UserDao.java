package com.dl.code.dao;

import com.dl.code.entity.User;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:29
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface UserDao {
    //分页查询,全部用户信息
    public List<User> selectAllUsers(int start,int pageSize);
    //查询总记录数
    public int selectCount();
    //分页模糊查询的总记录数
    public int selectLikeUserCount(String condition);
    //分页模糊查询
    public List<User> selectAllLikeUsers(String condition,int start,int pageSize);
    //删除用户信息
    public int deleteUserById(int id);
    //重置用户的密码
    public int resetPassword(int id);
    //根据用户名查询用户
    public List<User> selectAllUserByUsername(String username);
    //校验用户登录
    public int checkLogin(String username,String password);
    //用户注册
    public int register(User user);
}
