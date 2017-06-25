package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Dormitory extends DormitoryKey implements Serializable {
    /**
     * 可住人数
     */
    private Short maxNum;

    /**
     * 已住人数
     */
    private Short nowNum;

    private static final long serialVersionUID = 1L;

    public Short getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Short maxNum) {
        this.maxNum = maxNum;
    }

    public Short getNowNum() {
        return nowNum;
    }

    public void setNowNum(Short nowNum) {
        this.nowNum = nowNum;
    }
}