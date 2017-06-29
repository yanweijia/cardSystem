package entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class CourseChoice implements Serializable {
    /**
     * 学生编号
     */
    private String userId;

    /**
     * 课程编号
     */
    private Integer courseId;

    /**
     * 成绩
     */
    private BigDecimal grade;

    public CourseChoice() {
    }

    public CourseChoice(String userId, Integer courseId, BigDecimal grade) {
        this.userId = userId;
        this.courseId = courseId;
        this.grade = grade;
    }

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
}