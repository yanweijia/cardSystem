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

    private String buildingNo;
    private String dormitoryNo;

    @Override
    public String getBuildingNo() {
        return buildingNo;
    }

    @Override
    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    @Override
    public String getDormitoryNo() {
        return dormitoryNo;
    }

    @Override
    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }

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