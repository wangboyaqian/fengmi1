package com.dl.code.dao;

import com.dl.code.entity.Admin;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:32
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface AdminDao {
    public boolean findAdmin(String name,String password);
}
