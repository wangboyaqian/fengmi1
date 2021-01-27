package com.dl.code.dao.impl;

import com.dl.code.dao.OrderDao;
import com.dl.code.entity.Order;
import com.dl.code.entity.OrderStatus;
import com.dl.code.utils.BaseDao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 16:49
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class OrderDaoImpl implements OrderDao {
    private BaseDao baseDao = new BaseDao();
    @Override
    public List<Order> selectAllByPage(int start, int pageSize) {
        //SELECT t_order.id,t_order.orderCode,t_user
        String sql = "select t_order.id,t_order.orderCode,t_order.totalPrice,t_order.createDate,t_user.username,t_status.order_status from t_order inner join t_user on t_order.uid = t_user.id inner join t_status on t_order.status = t_status.id limit ?,?";
        Object[] params={start,pageSize};
        List<Order> list = new ArrayList<>();
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderCode(resultSet.getString("orderCode"));
                order.setTotalPrice(resultSet.getDouble("totalPrice"));
                order.setCreateDate(resultSet.getString("createDate"));
                order.setUname(resultSet.getString("username"));
                order.setStatusName(resultSet.getString("order_status"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int selectCount() {
        String sql = "select count(1) from t_order";
        Object[] params = {};
        int count = 0;
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                count = resultSet.getInt("count(1)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<OrderStatus> selectAllStatus() {
        String sql = "select * from t_status";
        Object[] params = {};
        List<OrderStatus> list = new ArrayList<>();
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(resultSet.getInt("id"));
                orderStatus.setOrder_status(resultSet.getString("order_status"));
                list.add(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Order> selectOrderByCondition(String condition,int start,int pageSize) {
        //SELECT * FROM `t_order` INNER JOIN t_user ON t_order.uid = t_user.id INNER JOIN t_status ON t_order.status = t_status.id WHERE t_status.order_status = '未付款'
        //SELECT * FROM `t_order` INNER JOIN t_user ON t_order.uid = t_user.id INNER JOIN t_status ON t_order.status = t_status.id WHERE t_status.order_status = '未付款'AND t_user.username = '李四'
        String sql = "SELECT * FROM t_order INNER JOIN t_user ON t_order.uid = t_user.id INNER JOIN t_status ON t_order.status = t_status.id WHERE 1=1 " + condition + " limit ?,?";
//        String sql = "select * from t_order where  "+condition;
        Object[] params = {start,pageSize};
        List<Order> list = new ArrayList<>();
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderCode(resultSet.getString("orderCode"));
                order.setTotalPrice(resultSet.getDouble("totalPrice"));
                order.setCreateDate(resultSet.getString("createDate"));
                order.setUname(resultSet.getString("username"));
                order.setStatusName(resultSet.getString("order_status"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int selectCountByCondition(String condition) {
        String sql = "select count(1) from t_order INNER JOIN t_user ON t_order.uid = t_user.id INNER JOIN t_status ON t_order.status = t_status.id where 1=1 "+condition;

        Object[] params = {};
        int count = 0;
        try {
            ResultSet resultSet = baseDao.select(sql, params);
            while (resultSet.next()){
                count = resultSet.getInt("count(1)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int deleteOrder(int id) {
        String sql = "delete from t_order where id =?";
        Object[] params = {id};
        int i = baseDao.setUpdate(sql, params);
        return i;
    }
}
