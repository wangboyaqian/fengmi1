package com.dl.code.entity;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/19 -- 13:11
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class GoodsType {
    private int id;
    private String typename;
    private int level;
    private int pid;

    public GoodsType() {
    }

    public GoodsType(int id, String typename, int level, int pid) {
        this.id = id;
        this.typename = typename;
        this.level = level;
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", typename='" + typename + '\'' +
                ", level=" + level +
                ", pid=" + pid +
                '}';
    }
}
