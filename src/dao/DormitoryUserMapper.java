package dao;

import entity.DormitoryUser;
import entity.DormitoryUserExample;
import entity.DormitoryUserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DormitoryUserMapper {
    long countByExample(DormitoryUserExample example);

    int deleteByExample(DormitoryUserExample example);

    int deleteByPrimaryKey(DormitoryUserKey key);

    int insert(DormitoryUser record);

    int insertSelective(DormitoryUser record);

    List<DormitoryUser> selectByExample(DormitoryUserExample example);

    DormitoryUser selectByPrimaryKey(DormitoryUserKey key);

    int updateByExampleSelective(@Param("record") DormitoryUser record, @Param("example") DormitoryUserExample example);

    int updateByExample(@Param("record") DormitoryUser record, @Param("example") DormitoryUserExample example);

    int updateByPrimaryKeySelective(DormitoryUser record);

    int updateByPrimaryKey(DormitoryUser record);
}