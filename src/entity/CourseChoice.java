package entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class CourseChoice extends CourseChoiceKey implements Serializable {
    /**
     * 成绩
     */
    private BigDecimal grade;

    private static final long serialVersionUID = 1L;

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
}