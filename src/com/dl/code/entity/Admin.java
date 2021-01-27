package com.dl.code.entity;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:30
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class Admin {
    private int id;
    private String username;
    private String password;
    private String sex;

    public Admin() {
    }

    public Admin(int id, String username, String password, String sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
