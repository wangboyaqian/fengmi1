package com.dl.code.entity;

import java.sql.Date;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 14:41
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class Goods {
    private int id;
    private String goodsName;
    private double price;
    private int score;
    private Date deployDate;
    private String imgPath;
    private String comment;
    private int typeId;
    private String typeName;

    public Goods() {
    }

    public Goods(int id, String goodsName, double price, int score, Date deployDate, String imgPath, String comment, int typeId, String typeName) {
        this.id = id;
        this.goodsName = goodsName;
        this.price = price;
        this.score = score;
        this.deployDate = deployDate;
        this.imgPath = imgPath;
        this.comment = comment;
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Goods(String goodsName, double price, Date deployDate, String imgPath, String comment, int typeId) {
        this.goodsName = goodsName;
        this.price = price;
        this.deployDate = deployDate;
        this.imgPath = imgPath;
        this.comment = comment;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDeployDate() {
        return deployDate;
    }

    public void setDeployDate(Date deployDate) {
        this.deployDate = deployDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", deployDate=" + deployDate +
                ", imgPath='" + imgPath + '\'' +
                ", comment='" + comment + '\'' +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
