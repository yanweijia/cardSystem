package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class DormitoryUser extends DormitoryUserKey implements Serializable {
    /**
     * 入住人
     */
    private String user_id;

    private static final long serialVersionUID = 1L;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}