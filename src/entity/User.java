package entity;

import java.io.Serializable;

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
}