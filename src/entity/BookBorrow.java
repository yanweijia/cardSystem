package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class BookBorrow implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 书籍编号
     */
    private Integer bookId;

    /**
     * 借书时间
     */
    private Date borrowtime;

    /**
     * 应还时间
     */
    private Date sBacktime;

    /**
     * 是否归还
     */
    private Boolean ifback;

    /**
     * 归还时间
     */
    private Date backtime;

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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(Date borrowtime) {
        this.borrowtime = borrowtime;
    }

    public Date getsBacktime() {
        return sBacktime;
    }

    public void setsBacktime(Date sBacktime) {
        this.sBacktime = sBacktime;
    }
    public Date getSBacktime() {
        return sBacktime;
    }

    public void setSBacktime(Date sBacktime) {
        this.sBacktime = sBacktime;
    }

    public Boolean getIfback() {
        return ifback;
    }

    public void setIfback(Boolean ifback) {
        this.ifback = ifback;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }
}