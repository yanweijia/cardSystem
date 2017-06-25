package dao;

import entity.BookAccount;
import entity.BookAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookAccountMapper {
    long countByExample(BookAccountExample example);

    int deleteByExample(BookAccountExample example);

    int deleteByPrimaryKey(String id);

    int insert(BookAccount record);

    int insertSelective(BookAccount record);

    List<BookAccount> selectByExample(BookAccountExample example);

    BookAccount selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BookAccount record, @Param("example") BookAccountExample example);

    int updateByExample(@Param("record") BookAccount record, @Param("example") BookAccountExample example);

    int updateByPrimaryKeySelective(BookAccount record);

    int updateByPrimaryKey(BookAccount record);
}