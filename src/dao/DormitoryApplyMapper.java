package dao;

import entity.DormitoryApply;
import entity.DormitoryApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DormitoryApplyMapper {
    long countByExample(DormitoryApplyExample example);

    int deleteByExample(DormitoryApplyExample example);

    int deleteByPrimaryKey(Integer applyId);

    int insert(DormitoryApply record);

    int insertSelective(DormitoryApply record);

    List<DormitoryApply> selectByExample(DormitoryApplyExample example);

    DormitoryApply selectByPrimaryKey(Integer applyId);

    int updateByExampleSelective(@Param("record") DormitoryApply record, @Param("example") DormitoryApplyExample example);

    int updateByExample(@Param("record") DormitoryApply record, @Param("example") DormitoryApplyExample example);

    int updateByPrimaryKeySelective(DormitoryApply record);

    int updateByPrimaryKey(DormitoryApply record);
}