package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class RequestModify implements Serializable {
    /**
     * 请求编号
     */
    private Integer id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 新电话
     */
    private String phone;

    /**
     * 新邮箱
     */
    private String email;

    /**
     * 新家庭住址
     */
    private String addr;

    /**
     * 是否已生效
     */
    private Boolean validate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }
}