package com.qf.mapper;

import com.qf.entity.Borrows;
import com.qf.entity.BorrowsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowsMapper {
    int countByExample(BorrowsExample example);

    int deleteByExample(BorrowsExample example);

    int deleteByPrimaryKey(Integer borrowId);

    int insert(Borrows record);

    int insertSelective(Borrows record);

    List<Borrows> selectByExample(BorrowsExample example);

    Borrows selectByPrimaryKey(Integer borrowId);

    int updateByExampleSelective(@Param("record") Borrows record, @Param("example") BorrowsExample example);

    int updateByExample(@Param("record") Borrows record, @Param("example") BorrowsExample example);

    int updateByPrimaryKeySelective(Borrows record);

    int updateByPrimaryKey(Borrows record);

    int checkBooksNumber(Integer bookStateId);
}