package com.dl.code.entity;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/20 -- 9:46
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class OrderStatus {
    private int id;
    private String order_status;

    public OrderStatus() {
    }

    public OrderStatus(int id, String order_status) {
        this.id = id;
        this.order_status = order_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", orderStatus='" + order_status + '\'' +
                '}';
    }
}
