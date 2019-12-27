package com.qf.service;

import com.qf.entity.Borrows;
import com.qf.util.Page;

/**
 * @author: JunJie
 * @className: IBorrowsService
 * @description: TODO
 * @createTime: 2019/12/20 15:09
 * @version: 1.0
 */
public interface IBorrowsService {
    /**
     * 判断书本余量
     *
     * @param bookId
     * @return
     */
    Boolean haveBooks(Integer bookId);

    /**
     * 插入新的借阅记录
     *
     * @param borrows 带有数据的 borrows 对象
     * @return 影响行数（插入结果 1成功 0失败
     */
    Integer insertBorrows(Borrows borrows);

    /**
     * 传入 borrowId 删除单个的借阅记录
     *
     * @param borrowId 对应的 borrowId
     * @return 影响行数（删除结果 1成功 0失败
     */
    Integer deleteBorrowsByBorrowId(Integer borrowId);


    /**
     * 传入 bookStateId 删除该在馆id的借阅情况
     *
     * @param bookStateId 在馆 id
     * @return 1/0
     */
    Integer deleteBorrowsByBookStateId(Integer bookStateId);

    /**
     * 根据 borrowId 批量删除
     *
     * @param borrowIds
     * @return
     */
    Integer deleteBatchByBorrowId(Integer[] borrowIds);

    /**
     * 通过 borrowId 修改对应的借阅记录
     *
     * @param borrows 需要修改借阅记录的 borrow 对象
     * @return 修改结果 （1成功 0失败
     */
    Integer updateBorrowsByBorrowId(Borrows borrows);

    /**
     * 通过 bookStateId 修改对应的借阅记录
     *
     * @param borrows 包含数据的对象
     * @return 修改结果
     */
    Integer updateBorrowsByBookStateId(Borrows borrows);

    /**
     * 根据对应的 borrow_id 查询对应的借阅记录
     *
     * @param borrowId 借阅事件对应的记录id
     * @return borrowId 对应的借阅记录
     */
    Borrows selectBorrowsByBorrowId(Integer borrowId);

    /**
     * 查询 bookStateId 记录
     *
     * @param bookStateId
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Borrows> selectBorrowsByBookStateId(Integer bookStateId, Integer currentPage, Integer pageSize);

    /**
     * 查询全部借阅记录
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Borrows> selectAllBorrows(Integer currentPage, Integer pageSize);

    /**
     * 分页查询所有未删除借阅记录
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<Borrows> selectAllUnDeleteBorrows(Integer currentPage, Integer pageSize);

    /**
     * 获取全部未删除借阅记录的数量
     *
     * @return All
     */
    Integer selectCountAllUnDeleteBorrows();


}
