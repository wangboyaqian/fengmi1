package com.dl.code.dao;

import com.dl.code.entity.Order;
import com.dl.code.entity.OrderStatus;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 16:48
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface OrderDao {

    public List<Order> selectAllByPage(int start,int pageSize);

    public int selectCount();

    public List<OrderStatus> selectAllStatus();

    public List<Order> selectOrderByCondition(String condition,int start,int pageSize);

    public int selectCountByCondition(String condition);

    public int deleteOrder(int id);
}
