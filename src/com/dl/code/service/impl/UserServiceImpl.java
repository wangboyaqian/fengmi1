package com.dl.code.service.impl;

import com.dl.code.dao.UserDao;
import com.dl.code.dao.impl.UserDaoImpl;
import com.dl.code.entity.User;
import com.dl.code.service.UserService;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:30
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> getUserByPage(int pageNo, int pageSize) {
        //计算分页查询开始的位置
        int start = (pageNo-1)*pageSize;
        //调用方法分页查询方法
        List<User> users = userDao.selectAllUsers(start, pageSize);
        return users;
    }

    @Override
    public int getDataCount() {
        //调用方法
        int dataCount = userDao.selectCount();
        return dataCount;
    }

    @Override
    public List<User> getAllLikeUsers(String condition, int pageNo, int pageSize) {
        //计算分页查询开始的位置
        int start = (pageNo-1)*pageSize;
        //调用方法分页查询方法
        List<User> users = userDao.selectAllLikeUsers(condition, start, pageSize);
        return users;
    }

    @Override
    public int getAllLikeUsersCount(String condition) {
        int count = userDao.selectLikeUserCount(condition);
        return count;
    }

    @Override
    public int deleteUser(int id) {
        int i = userDao.deleteUserById(id);
        return i;
    }

    @Override
    public int resetPassword(int id) {
        int i = userDao.resetPassword(id);
        return i;
    }

    @Override
    public List<User> getAllUserByUsername(String username) {
        List<User> users = userDao.selectAllUserByUsername(username);
        return users;
    }

    @Override
    public int checkLogin(String username, String password) {
        int i = userDao.checkLogin(username, password);
        return i;
    }

    @Override
    public int register(User user) {
        int register = userDao.register(user);
        return register;
    }
}
