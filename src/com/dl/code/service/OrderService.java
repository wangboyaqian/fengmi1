package com.dl.code.service;

import com.dl.code.entity.Order;
import com.dl.code.entity.OrderStatus;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 16:49
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public interface OrderService {

    public List<Order> getAllOrdersById(int pageNo,int pageSize);

    public int getDataCount();

    public List<OrderStatus> getAllStatus();

    public List<Order> getAllOrderByCondition(String condition,int pageNo,int pageSize);

    //获得总记录数
    public int getDataCount(String condition);

    //删除订单
    public int deleteOrder(int id);


}
