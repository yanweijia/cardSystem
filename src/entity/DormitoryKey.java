package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class DormitoryKey implements Serializable {
    /**
     * 楼号
     */
    private String buildingNo;

    /**
     * 宿舍号
     */
    private String dormitoryNo;

    private static final long serialVersionUID = 1L;

    public DormitoryKey() {
    }

    public DormitoryKey(String buildingNo, String dormitoryNo) {
        this.buildingNo = buildingNo;
        this.dormitoryNo = dormitoryNo;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getDormitoryNo() {
        return dormitoryNo;
    }

    public void setDormitoryNo(String dormitoryNo) {
        this.dormitoryNo = dormitoryNo;
    }
}