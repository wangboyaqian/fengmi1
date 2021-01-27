package com.dl.code.entity;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 16:45
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class Order {
    private int id;
    private String orderCode;
    private double totalPrice;
    private String createDate;
    private int uid;
    private int status;
    private String uname;
    private String statusName;

    public Order(int id, String orderCode, double totalPrice, String createDate, int uid, int status, String uname, String statusName) {
        this.id = id;
        this.orderCode = orderCode;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.uid = uid;
        this.status = status;
        this.uname = uname;
        this.statusName = statusName;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", totalPrice=" + totalPrice +
                ", createDate='" + createDate + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                ", uname='" + uname + '\'' +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
