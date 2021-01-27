package com.dl.code.dao.impl;

import com.dl.code.dao.AdminDao;
import com.dl.code.entity.Admin;
import com.dl.code.utils.BaseDao;

import java.sql.ResultSet;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:32
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class AdminDaoImpl implements AdminDao {
    private BaseDao baseDao;

    @Override
    public boolean findAdmin(String name, String password) {
        //定义一个标记,用来验证是否允许登录
        boolean flag = false;
        //定义sql语句
        String sql = "select * from admin where username = ? and password = ?";
        //定义参数
        Object[] params = {name,password};
        //实例化BaseDao
        baseDao = new BaseDao();
        try {
            //调用方法执行sql
            ResultSet results = baseDao.select(sql, params);
            //遍历集合
            while (results.next()){
                //如果有值存在,则允许登录,在这里将标记改为true
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
