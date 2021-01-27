package com.dl.code.dao.impl;

import com.dl.code.dao.UserDao;
import com.dl.code.entity.User;
import com.dl.code.utils.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:29
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class UserDaoImpl implements UserDao {
    private BaseDao baseDao = new BaseDao();
    @Override
    public List<User> selectAllUsers(int start, int pageSize) {
        //定义sql语句
        String sql = "select * from t_user limit ?,?";
        //定义参数数组
        Object[] params = {start,pageSize};
        //实例化一个List集合
        List<User> list = new ArrayList<>();
        //实例化一个User对象
        User user = null;
        try {
            //执行sql语句
            ResultSet users = baseDao.select(sql, params);
            //取出ResultSet中的值
            while (users.next()){
                user = new User();
                //取出集合中的值,并将对应的值赋值给User对象
                user.setId(users.getInt("id"));
                user.setUsername(users.getString("username"));
                user.setPhone(users.getString("phone"));
                user.setSex(users.getString("sex"));
                user.setEmail(users.getString("email"));
                //将赋值后的User对象添加到ArrayList集合中
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int selectCount(){
        //定义一个sql语句
        String sql = "select * from t_user";
        //定义参数
        Object[] objects = {};
        int count = 0;
        try {
            //执行sql
            ResultSet results = baseDao.select(sql, objects);
            results.last();
            count = results.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int selectLikeUserCount(String condition) {
        String sql = "select * from t_user where 1=1 "+condition;
        Object[] params = {};
        int count = 0;
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            resultSet.last();
            count = resultSet.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<User> selectAllLikeUsers(String condition, int start, int pageSize) {
        //定义一个sql语句
        String sql = "select * from t_user where 1=1 "+condition+" limit ?,?";
        Object[] objects={start,pageSize};
        List<User> list = new ArrayList<>();
        User user = null;
        try {
            ResultSet users = baseDao.select(sql, objects);
            //取出ResultSet中的值
            while (users.next()){
                user = new User();
                //取出集合中的值,并将对应的值赋值给User对象
                user.setId(users.getInt("id"));
                user.setUsername(users.getString("username"));
                user.setPhone(users.getString("phone"));
                user.setSex(users.getString("sex"));
                user.setEmail(users.getString("email"));
                //将赋值后的User对象添加到ArrayList集合中
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int deleteUserById(int id) {
        //定义sql
        String sql = "delete from t_user where id = ?";
        Object[] objects = {id};
        int i = baseDao.setUpdate(sql, objects);
        return i;
    }

    @Override
    public int resetPassword(int id) {
        String sql = "update t_user set password = ? where id = ?";
        Object[] params = {"000000",id};
        int i = baseDao.setUpdate(sql, params);
        return i;
    }

    @Override
    public List<User> selectAllUserByUsername(String username) {
        String sql = "select * from t_user where username = ?";
        Object[] params = {username};
        List<User> list = new ArrayList<>();
        User user = null;
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                user = new User();
                //取出集合中的值,并将对应的值赋值给User对象
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhone(resultSet.getString("phone"));
                user.setSex(resultSet.getString("sex"));
                user.setEmail(resultSet.getString("email"));
                //将赋值后的User对象添加到ArrayList集合中
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int checkLogin(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";
        Object[] params = {username,password};
        int count = 0;
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            resultSet.last();
            count = resultSet.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int register(User user) {
        String sql = "INSERT INTO  t_user (username, password, phone, sex, email) VALUES(?,?,?,?,?)";
        Object[] params = {user.getUsername(),user.getPassword(),user.getPhone(),user.getSex(),user.getEmail()};
        int i = baseDao.setUpdate(sql, params);
        return i;
    }
}
