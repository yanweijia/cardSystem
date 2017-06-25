package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class BookAccount implements Serializable {
    /**
     * 用户编号
     */
    private String id;

    /**
     * 最大借阅数
     */
    private Short maxBorrowNum;

    /**
     * 已借阅数
     */
    private Short borrowedNum;

    /**
     * 用户注册日期
     */
    private Date registerDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getMaxBorrowNum() {
        return maxBorrowNum;
    }

    public void setMaxBorrowNum(Short maxBorrowNum) {
        this.maxBorrowNum = maxBorrowNum;
    }

    public Short getBorrowedNum() {
        return borrowedNum;
    }

    public void setBorrowedNum(Short borrowedNum) {
        this.borrowedNum = borrowedNum;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}