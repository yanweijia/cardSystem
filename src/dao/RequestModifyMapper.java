package dao;

import entity.RequestModify;
import entity.RequestModifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequestModifyMapper {
    long countByExample(RequestModifyExample example);

    int deleteByExample(RequestModifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RequestModify record);

    int insertSelective(RequestModify record);

    List<RequestModify> selectByExample(RequestModifyExample example);

    RequestModify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RequestModify record, @Param("example") RequestModifyExample example);

    int updateByExample(@Param("record") RequestModify record, @Param("example") RequestModifyExample example);

    int updateByPrimaryKeySelective(RequestModify record);

    int updateByPrimaryKey(RequestModify record);
}