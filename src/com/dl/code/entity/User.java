package com.dl.code.entity;

/**
 * @公司 DL19121630工作室
 * @作者 代先生
 * @日期 2021/1/18 -- 17:24
 * @微信 D19121630L
 * @温馨提示：原创代码，翻版必究！如需代写，微信联系！
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String email;
    private String emailStatus;
    private String validateCode;

    public User() {
    }

    public User(int id, String username, String password, String phone, String sex, String email, String emailStatus, String validateCode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.email = email;
        this.emailStatus = emailStatus;
        this.validateCode = validateCode;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", emailStatus='" + emailStatus + '\'' +
                ", validateCode='" + validateCode + '\'' +
                '}';
    }
}
