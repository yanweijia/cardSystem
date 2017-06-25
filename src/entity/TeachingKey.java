package entity;

import java.io.Serializable;

/**
 * @author 
 */
public class TeachingKey implements Serializable {
    /**
     * 教师编号
     */
    private String teacherId;

    /**
     * 课程编号
     */
    private Integer courseId;

    private static final long serialVersionUID = 1L;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}