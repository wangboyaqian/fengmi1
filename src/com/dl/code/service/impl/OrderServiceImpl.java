package com.dl.code.service.impl;

import com.dl.code.dao.OrderDao;
import com.dl.code.dao.impl.OrderDaoImpl;
import com.dl.code.entity.Order;
import com.dl.code.entity.OrderStatus;
import com.dl.code.service.OrderService;

import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 16:49
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public List<Order> getAllOrdersById(int pageNo, int pageSize) {
        int start = (pageNo-1)*pageSize;
        List<Order> list = orderDao.selectAllByPage(start, pageSize);
        return list;
    }

    @Override
    public int getDataCount() {
        int dataCount = orderDao.selectCount();
        return dataCount;
    }

    @Override
    public List<OrderStatus> getAllStatus() {
        List<OrderStatus> orderStatuses = orderDao.selectAllStatus();
        return orderStatuses;
    }

    @Override
    public List<Order> getAllOrderByCondition(String condition,int pageNo,int pageSize) {
        int start = (pageNo-1)*pageSize;
        List<Order> list = orderDao.selectOrderByCondition(condition,start,pageSize);
        return list;
    }

    @Override
    public int getDataCount(String condition) {
        int i = orderDao.selectCountByCondition(condition);
        return i;
    }

    @Override
    public int deleteOrder(int id) {
        int i = orderDao.deleteOrder(id);
        return i;
    }
}
