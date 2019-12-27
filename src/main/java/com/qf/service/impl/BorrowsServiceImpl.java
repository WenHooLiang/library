package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.entity.Borrows;
import com.qf.entity.BorrowsExample;
import com.qf.mapper.BorrowsMapper;
import com.qf.service.IBorrowsService;
import com.qf.util.ChangliangUtil;
import com.qf.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author: JunJie
 * @className: BorrowsServiceImpl
 * @description: TODO
 * @createTime: 2019/12/20 15:21
 * @version: 1.0
 */
@Service
public class BorrowsServiceImpl implements IBorrowsService {
    @Autowired
    BorrowsMapper borrowsMapper;

    @Override
    public Boolean haveBooks(Integer bookStateId) {
        return borrowsMapper.checkBooksNumber(bookStateId) > 0;
    }

    /**
     * 插入新的借阅记录
     *
     * @param borrows 带有数据的 borrows 对象
     * @return 影响行数
     */
    @Override
    public Integer insertBorrows(Borrows borrows) {
        if (borrows.getReaderId() != null && borrows.getReaderId() > 0
                && borrows.getBookStateId() != null && borrows.getBookStateId() > 0) {
            return borrowsMapper.insertSelective(borrows);
        }
        return ChangliangUtil.FAIL;
    }

    @Override
    public Integer deleteBorrowsByBorrowId(Integer borrowsId) {
        if (borrowsId != null && borrowsId > 0) {
            BorrowsExample borrowsExample = new BorrowsExample();
            borrowsExample.createCriteria().andBorrowIdEqualTo(borrowsId).andIsDeleteEqualTo(1);
            Borrows borrows = new Borrows();
            borrows.setIsDelete(0);
            return borrowsMapper.updateByExampleSelective(borrows, borrowsExample);
        }
        return ChangliangUtil.FAIL;
    }

    @Override
    public Integer deleteBorrowsByBookStateId(Integer bookStateId) {
        if (bookStateId != null && bookStateId > 0) {
            BorrowsExample borrowsExample = new BorrowsExample();
            borrowsExample.createCriteria().andBookStateIdEqualTo(bookStateId).andIsDeleteEqualTo(1);
            Borrows borrows = new Borrows();
            borrows.setIsDelete(0);
            return borrowsMapper.updateByExampleSelective(borrows, borrowsExample);
        }
        return ChangliangUtil.FAIL;
    }

    @Override
    public Integer deleteBatchByBorrowId(Integer[] borrowIds) {
        int deleteCount = 0;
        if (!StringUtils.isEmpty(borrowIds) && borrowIds.length > 0) {
            for (Integer borrowId : borrowIds) {
                BorrowsExample borrowsExample = new BorrowsExample();
                borrowsExample.createCriteria().andIsDeleteEqualTo(1).andBorrowIdEqualTo(borrowId);
                Borrows borrows = new Borrows();
                borrows.setIsDelete(0);
                if (borrowsMapper.updateByExampleSelective(borrows, borrowsExample) == 1) {
                    deleteCount++;
                }
            }
        }
        return deleteCount;
    }


    @Override
    public Integer updateBorrowsByBorrowId(Borrows borrows) {
        if (borrows.getBorrowId() != null && borrows.getBorrowId() > 0) {
            BorrowsExample borrowsExample = new BorrowsExample();
            borrowsExample.createCriteria().andBorrowIdEqualTo(borrows.getBorrowId());
            return borrowsMapper.updateByExampleSelective(borrows, borrowsExample);
        }
        return ChangliangUtil.FAIL;
    }

    @Override
    public Integer updateBorrowsByBookStateId(Borrows borrows) {
        if (borrows.getBookStateId() != null && borrows.getBookStateId() > 0) {
            BorrowsExample borrowsExample = new BorrowsExample();
            borrowsExample.createCriteria().andBookStateIdEqualTo(borrows.getBookStateId());
            return borrowsMapper.updateByExampleSelective(borrows, borrowsExample);
        }
        return ChangliangUtil.FAIL;
    }


    /**
     * 根据 borrowsId 查询对应借阅事件
     *
     * @param borrowId 借阅事件对应的记录id
     * @return borrowsId 对应的借阅记录
     */
    @Override
    public Borrows selectBorrowsByBorrowId(Integer borrowId) {
        if (borrowId != null && borrowId > 0) {
            Borrows borrows = borrowsMapper.selectByPrimaryKey(borrowId);
            return borrows;
        }
        return null;
    }

    @Override
    public Page<Borrows> selectBorrowsByBookStateId(Integer bookStateId, Integer currentPage, Integer pageSize) {
        if (bookStateId != null && bookStateId > 0) {
            PageHelper.startPage(currentPage, pageSize);

            BorrowsExample borrowsExample = new BorrowsExample();
            borrowsExample.createCriteria().andBookStateIdEqualTo(bookStateId).andIsDeleteEqualTo(1);

            List<Borrows> borrowsList = borrowsMapper.selectByExample(borrowsExample);
            int totalCount = borrowsMapper.countByExample(borrowsExample);
            if (!borrowsList.isEmpty()) {
                Page<Borrows> page = new Page<>(currentPage, pageSize, totalCount);
                page.setList(borrowsList);
                return page;
            }
        }
        return null;
    }


    /**
     * 查询全部借阅记录
     *
     * @return 全部记录的list
     */
    @Override
    public Page<Borrows> selectAllBorrows(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);

        BorrowsExample borrowsExample = new BorrowsExample();
        List<Borrows> borrowsList = borrowsMapper.selectByExample(borrowsExample);
        int totalCount = borrowsMapper.countByExample(borrowsExample);

        Page<Borrows> page = new Page<>(currentPage, pageSize, totalCount);
        page.setList(borrowsList);
        return page;
    }

    @Override
    public Page<Borrows> selectAllUnDeleteBorrows(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);

        BorrowsExample borrowsExample = new BorrowsExample();
        borrowsExample.createCriteria().andIsDeleteEqualTo(1);
        List<Borrows> borrowsList = borrowsMapper.selectByExample(borrowsExample);

        int totalCount = borrowsMapper.countByExample(borrowsExample);

        Page<Borrows> page = new Page<>(currentPage, pageSize, totalCount);
        page.setList(borrowsList);

        return page;
    }

    /**
     * 全部未删除的借阅记录数量
     *
     * @return 数量
     */
    @Override
    public Integer selectCountAllUnDeleteBorrows() {
        BorrowsExample borrowsExample = new BorrowsExample();
        borrowsExample.createCriteria().andIsDeleteEqualTo(1);
        return borrowsMapper.countByExample(borrowsExample);
    }

}
