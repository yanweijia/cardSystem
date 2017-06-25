package dao;

import entity.BookBorrow;
import entity.BookBorrowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookBorrowMapper {
    long countByExample(BookBorrowExample example);

    int deleteByExample(BookBorrowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookBorrow record);

    int insertSelective(BookBorrow record);

    List<BookBorrow> selectByExample(BookBorrowExample example);

    BookBorrow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookBorrow record, @Param("example") BookBorrowExample example);

    int updateByExample(@Param("record") BookBorrow record, @Param("example") BookBorrowExample example);

    int updateByPrimaryKeySelective(BookBorrow record);

    int updateByPrimaryKey(BookBorrow record);
}