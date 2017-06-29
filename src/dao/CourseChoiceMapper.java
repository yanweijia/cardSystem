package dao;

import entity.CourseChoice;
import entity.CourseChoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseChoiceMapper {
    long countByExample(CourseChoiceExample example);

    int deleteByExample(CourseChoiceExample example);

    int insert(CourseChoice record);

    int insertSelective(CourseChoice record);

    List<CourseChoice> selectByExample(CourseChoiceExample example);

    int updateByExampleSelective(@Param("record") CourseChoice record, @Param("example") CourseChoiceExample example);

    int updateByExample(@Param("record") CourseChoice record, @Param("example") CourseChoiceExample example);
}