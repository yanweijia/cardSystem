package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class CourseChoiceKey implements Serializable {
    /**
     * 学生编号
     */
    private String userId;

    /**
     * 课程编号
     */
    private Integer courseId;

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
}