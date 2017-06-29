package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DormitoryApply implements Serializable {
    /**
     * 申请编号
     */
    private Integer applyId;

    /**
     * 申请人
     */
    private String userId;

    /**
     * 是否随机
     */
    private Boolean radnom;

    /**
     * 楼号
     */
    private String buildingNo;

    /**
     * 宿舍号
     */
    private String dormitoryNo;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 是否通过
     */
    private Boolean result;

    public DormitoryApply() {
    }

    public DormitoryApply(Integer applyId, String userId, Boolean radnom, String buildingNo, String dormitoryNo, Date applyTime, Boolean result) {
        this.applyId = applyId;
        this.userId = userId;
        this.radnom = radnom;
        this.buildingNo = buildingNo;
        this.dormitoryNo = dormitoryNo;
        this.applyTime = applyTime;
        this.result = result;
    }

    private static final long serialVersionUID = 1L;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getRadnom() {
        return radnom;
    }

    public void setRadnom(Boolean radnom) {
        this.radnom = radnom;
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

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}