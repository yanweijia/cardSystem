package dao;

import entity.CourseChoice;
import entity.CourseChoiceExample;
import entity.CourseChoiceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseChoiceMapper {
    long countByExample(CourseChoiceExample example);

    int deleteByExample(CourseChoiceExample example);

    int deleteByPrimaryKey(CourseChoiceKey key);

    int insert(CourseChoice record);

    int insertSelective(CourseChoice record);

    List<CourseChoice> selectByExample(CourseChoiceExample example);

    CourseChoice selectByPrimaryKey(CourseChoiceKey key);

    int updateByExampleSelective(@Param("record") CourseChoice record, @Param("example") CourseChoiceExample example);

    int updateByExample(@Param("record") CourseChoice record, @Param("example") CourseChoiceExample example);

    int updateByPrimaryKeySelective(CourseChoice record);

    int updateByPrimaryKey(CourseChoice record);
}