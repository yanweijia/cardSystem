package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class DormitoryUserKey implements Serializable {
    /**
     * 楼号
     */
    private String building_no;

    /**
     * 宿舍号
     */
    private String dormitory_no;

    /**
     * 床号
     */
    private String bed_no;

    private static final long serialVersionUID = 1L;

    public String getBuilding_no() {
        return building_no;
    }

    public void setBuilding_no(String building_no) {
        this.building_no = building_no;
    }

    public String getDormitory_no() {
        return dormitory_no;
    }

    public void setDormitory_no(String dormitory_no) {
        this.dormitory_no = dormitory_no;
    }

    public String getBed_no() {
        return bed_no;
    }

    public void setBed_no(String bed_no) {
        this.bed_no = bed_no;
    }
}