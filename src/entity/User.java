package entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author
 */
public class User implements Serializable {
    /**
     * 用户编号(学号,教职工号等)
     */
    private String id;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 用户状态是否可用
     */
    private String available;

    /**
     * 姓名
     */
    private String name;

    /**
     * 一卡通余额
     */
    private BigDecimal balance;

    /**
     * 用户组织机构
     */
    private Integer organization;

    /**
     * 科室/班级
     */
    private Integer section;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 家庭住址
     */
    private String addr;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getOrganization() {
        return organization;
    }

    public void setOrganization(Integer organization) {
        this.organization = organization;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}