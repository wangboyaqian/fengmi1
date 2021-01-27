package com.dl.code.service.impl;

import com.dl.code.dao.AdminDao;
import com.dl.code.dao.UserDao;
import com.dl.code.dao.impl.AdminDaoImpl;
import com.dl.code.dao.impl.UserDaoImpl;
import com.dl.code.service.AdminService;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:33
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;
    @Override
    public boolean checkLogin(String name, String password) {
        //实例化一个userDao对象
        adminDao = new AdminDaoImpl();
        //调用方法
        boolean flag = adminDao.findAdmin(name, password);
        return flag;
    }
}
