package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class BookInfo implements Serializable {
    /**
     * 图书编号
     */
    private Integer bookId;

    /**
     * 名称
     */
    private String name;

    /**
     * 图书类别
     */
    private String type;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社名称
     */
    private String publisher;

    /**
     * 出版日期
     */
    private Date pubDate;

    /**
     * 登记日期
     */
    private Date registerDate;

    /**
     * 图书价格
     */
    private BigDecimal price;

    /**
     * ISBN编号
     */
    private String isbn;

    /**
     * 书架及位置
     */
    private String bookrack;

    /**
     * 书籍总共本数
     */
    private Short totalNum;

    /**
     * 剩余本数
     */
    private Short remainNum;

    private static final long serialVersionUID = 1L;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookrack() {
        return bookrack;
    }

    public void setBookrack(String bookrack) {
        this.bookrack = bookrack;
    }

    public Short getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Short totalNum) {
        this.totalNum = totalNum;
    }

    public Short getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Short remainNum) {
        this.remainNum = remainNum;
    }
}