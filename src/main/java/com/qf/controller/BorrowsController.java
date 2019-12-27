package com.qf.controller;

import com.qf.constan.StateCode;
import com.qf.controller.base.Base;
import com.qf.entity.Borrows;
import com.qf.mapper.SystemMapper;
import com.qf.service.IBorrowsService;
import com.qf.util.ChangliangUtil;
import com.qf.util.Page;
import com.qf.util.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author: JunJie
 * @classBelongTo: library.com.qf.controller
 * @description: TODO
 * @createTime: 2019/12/23 11:57
 * @version: 1.0
 */
@RestController
@RequestMapping("/borrows")
public class BorrowsController extends Base {
    @Autowired
    IBorrowsService borrowsService;

    @Autowired
    SystemMapper systemMapper;

    /**
     * 插入新借阅情况,测试通过
     *
     * @param borrows
     * @return
     */
    @PostMapping("/insertBorrows")
    public State<Object> insertBorrows(Borrows borrows) {
        if (borrows != null && borrowsService.haveBooks(borrows.getBookStateId())) {
            borrows.setBorrowTime(new Date(System.currentTimeMillis()));
            return borrowsService.insertBorrows(borrows) > 0 ?
                    packaging(StateCode.SUCCESS, "新借阅情况" + ChangliangUtil.INSERTSUCCESS, null)
                    : packaging(StateCode.FAIL, "新借阅情况" + ChangliangUtil.INSERTFAIL, null);
        }
        return packaging(StateCode.FAIL, "信息不完整", null);
    }

    /**
     * 删除 borrowId 对应借阅情况，测试通过
     *
     * @param borrowId
     * @return
     */
    @DeleteMapping("/deleteBorrowsByBorrowId")
    public State<Object> deleteBorrowsByBorrowId(Integer borrowId) {
        if (borrowId != null && borrowId > 0) {
            return borrowsService.deleteBorrowsByBorrowId(borrowId) > 0 ?
                    packaging(StateCode.SUCCESS, "borrowId:" + borrowId + "的借阅情况" + ChangliangUtil.DELETESUCCESS, null)
                    : packaging(StateCode.FAIL, "borrowId:" + borrowId + "的借阅情况" + ChangliangUtil.DELETEFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息为空", null);
    }

    /**
     * 删除 bookStateId 对应借阅情况，测试通过
     *
     * @param bookStateId
     * @return
     */
    @DeleteMapping("/deleteBorrowsByBookStateId")
    public State<Object> deleteBorrowsByBookStateId(Integer bookStateId) {
        if (bookStateId != null && bookStateId > 0) {
            return borrowsService.deleteBorrowsByBookStateId(bookStateId) > 0 ?
                    packaging(StateCode.SUCCESS, "bookStateId:" + bookStateId + "的借阅情况" + ChangliangUtil.DELETESUCCESS, null)
                    : packaging(StateCode.FAIL, "bookStateId:" + bookStateId + "的借阅情况" + ChangliangUtil.DELETEFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息为空", null);
    }

    /**
     * 通过 borrowId 批量删除
     *
     * @param borrowIds
     * @return
     */
    @DeleteMapping("/deleteBatchByBorrowId")
    public State<Object> deleteBatchByBorrowId(String borrowIds) {
        if (StringUtils.isEmpty(borrowIds)) {
            return packaging(StateCode.FAIL, "删除失败", null);
        }
        String[] nums = borrowIds.split(",");

        Integer[] ints = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ints[i] = Integer.parseInt(nums[i]);
        }
        if (ints.length > 0) {
            return borrowsService.deleteBatchByBorrowId(ints) > 0 ?
                    packaging(StateCode.SUCCESS, "批量删除借阅情况" + ChangliangUtil.DELETESUCCESS, null)
                    : packaging(StateCode.FAIL, "批量删除借阅情况" + ChangliangUtil.DELETEFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息为空", null);
    }

    /**
     * 通过 borrowId 更新单一记录，测试通过
     *
     * @param borrows
     * @return
     */
    @PostMapping("/updateBorrowsByBorrowId")
    public State<Object> updateBorrowsByBorrowId(Borrows borrows) {
        if (borrows != null && borrows.getBorrowId() != null && borrows.getBorrowId() > 0) {
            return borrowsService.updateBorrowsByBorrowId(borrows) > 0 ?
                    packaging(StateCode.SUCCESS, "借阅情况" + ChangliangUtil.UPDATESUCCESS, null)
                    : packaging(StateCode.FAIL, "借阅情况" + ChangliangUtil.UPDATEFAIL, null);
        }
        return packaging(StateCode.FAIL, "信息不完整，或请传入BorrowId", null);
    }

    /**
     * 通过 bookStateId 更新对应记录，测试通过
     *
     * @param borrows
     * @return
     */
    @PutMapping("/updateBorrowsByBookStateId")
    public State<Object> updateBorrowsByBookStateId(Borrows borrows) {
        if (borrows != null && borrows.getBookStateId() != null && borrows.getBookStateId() > 0) {
            return borrowsService.updateBorrowsByBookStateId(borrows) > 0 ?
                    packaging(StateCode.SUCCESS, "借阅情况" + ChangliangUtil.UPDATESUCCESS, null)
                    : packaging(StateCode.FAIL, "借阅情况" + ChangliangUtil.UPDATEFAIL, null);
        }
        return packaging(StateCode.FAIL, "信息不完整，或请传入BookStateId", null);
    }

    /**
     * 通过 borrowId 查询对应单一记录，测试通过
     *
     * @param borrowId
     * @return
     */
    @GetMapping("/selectBorrowsByBorrowId")
    public State<Object> selectBorrowsByBorrowId(Integer borrowId) {
        if (borrowId != null && borrowId > 0) {
            Borrows borrows = borrowsService.selectBorrowsByBorrowId(borrowId);
            return borrows != null ?
                    packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, borrows)
                    : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息不完整", null);
    }

    /**
     * 通过 bookStateId 查询对应记录，测试通过
     *
     * @param bookStateId
     * @return
     */
    @GetMapping("/selectBorrowsByBookStateId")
    public State<Object> selectBorrowsByBookStateId(Integer bookStateId, Integer currentPage, Integer pageSize) {

        if (currentPage == null) {
            return packaging(StateCode.FAIL, "请输入当前页", null);
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = systemMapper.getPageLine();
        }
        if (bookStateId != null && bookStateId > 0) {
            Page<Borrows> pageList = borrowsService.selectBorrowsByBookStateId(bookStateId, currentPage, pageSize);
            return pageList.getList().size() != 0 ?
                    packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, pageList)
                    : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息不完整", null);
    }

    /**
     * 分页查询全部借阅情况，测试通过
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectAllBorrows")
    public State<Object> selectAllBorrows(Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            return packaging(StateCode.FAIL, "请输入当前页", null);
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = systemMapper.getPageLine();
        }
        Page<Borrows> pageList = borrowsService.selectAllBorrows(currentPage, pageSize);
        return pageList.getList().size() != 0 ?
                packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, pageList)
                : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
    }

    /**
     * 分页查询对应未删除借阅情况，测试通过
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectAllUnDeleteBorrows")
    public State<Object> selectAllUnDeleteBorrows(Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            return packaging(StateCode.FAIL, "请输入当前页", null);
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = systemMapper.getPageLine();
        }
        Page<Borrows> pageList = borrowsService.selectAllUnDeleteBorrows(currentPage, pageSize);
        return pageList.getList().size() != 0 ?
                packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, pageList)
                : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
    }

}
