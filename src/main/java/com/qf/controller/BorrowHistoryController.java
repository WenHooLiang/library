package com.qf.controller;

import com.qf.constan.StateCode;
import com.qf.controller.base.Base;
import com.qf.entity.BorrowHistory;
import com.qf.mapper.SystemMapper;
import com.qf.service.IBorrowHistoryService;
import com.qf.util.ChangliangUtil;
import com.qf.util.Page;
import com.qf.util.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: JunJie
 * @classBelongTo: library.com.qf.controller
 * @description: TODO
 * @createTime: 2019/12/23 11:58
 * @version: 1.0
 */
@RestController
@RequestMapping("/borrowHistory")
public class BorrowHistoryController extends Base {
    @Autowired
    IBorrowHistoryService borrowHistoryService;

    @Autowired
    SystemMapper systemMapper;

    /**
     * 插入新借阅历史，已测试
     *
     * @param borrowHistory
     * @return
     */
    @PostMapping("/insertBorrowHistory")
    public State<Object> insertBorrowHistory(BorrowHistory borrowHistory) {
        if (borrowHistory != null) {
//            borrowHistory.setBorrowTime(new Date(System.currentTimeMillis() - new Random().nextInt(10) * 24 * 60));
//            borrowHistory.setRepayTime(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000));
       return borrowHistoryService.insertBorrowHistory(borrowHistory) > 0 ?
                    packaging(StateCode.SUCCESS, "新借阅历史" + ChangliangUtil.INSERTSUCCESS, null)
                    : packaging(StateCode.FAIL, "新借阅历史" + ChangliangUtil.INSERTFAIL, null);
        }
        return packaging(StateCode.FAIL, "信息不完整", null);
    }

    /**
     * 通过 history Id 删除单个借阅历史,已测试
     *
     * @param historyId
     * @return
     */
    @DeleteMapping("/deleteBorrowHistoryByHistoryId")
    public State<Object> deleteBorrowHistoryByHistoryId(Integer historyId) {
        if (historyId != null && historyId > 0) {
            return borrowHistoryService.deleteBorrowHistoryByHistoryId(historyId) > 0 ?
                    packaging(StateCode.SUCCESS, "HistoryId:" + historyId + "的借阅历史" + ChangliangUtil.DELETESUCCESS, null)
                    : packaging(StateCode.FAIL, "HistoryId:" + historyId + "的借阅历史" + ChangliangUtil.DELETEFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息为空", null);
    }

    /**
     * 通过 book ID 删除对应全部借阅历史，已测试
     *
     * @param bookId
     * @return
     */
    @DeleteMapping("/deleteBorrowHistoryByBookId")
    public State<Object> deleteBorrowHistoryByBookId(Integer bookId) {
        if (bookId != null && bookId > 0) {
            return borrowHistoryService.deleteBorrowHistoryByBookId(bookId) > 0 ?
                    packaging(StateCode.SUCCESS, "BookId:" + bookId + "的借阅历史" + ChangliangUtil.DELETESUCCESS, null)
                    : packaging(StateCode.FAIL, "BookId:" + bookId + "的借阅历史" + ChangliangUtil.DELETEFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息为空", null);
    }

    /**
     * 通过 readerId 删除对应读者全部借阅历史，已测试
     *
     * @param readerId
     * @return
     */
    @DeleteMapping("/deleteBorrowHistoryByReaderId")
    public State<Object> deleteBorrowHistoryByReaderId(Integer readerId) {
        if (readerId != null && readerId > 0) {
            return borrowHistoryService.deleteBorrowHistoryByReaderId(readerId) > 0 ?
                    packaging(StateCode.SUCCESS, "readerId:" + readerId + "的借阅历史" + ChangliangUtil.DELETESUCCESS, null)
                    : packaging(StateCode.FAIL, "readerId:" + readerId + "的借阅历史" + ChangliangUtil.DELETEFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息为空", null);
    }

    /**
     * 通过 historyId 批量删除
     *
     * @param deleteIds
     * @return
     */
    @DeleteMapping("/deleteBatchByHistoryId")
    public State<Object> deleteBatchByHistoryId(String deleteIds) {
        if (StringUtils.isEmpty(deleteIds)){
            return packaging(StateCode.FAIL,"删除失败",null);
        }
        String[] nums = deleteIds.split(",");

        Integer[] ints = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ints[i]= Integer.parseInt(nums[i]);
        }
        if (ints.length > 0) {
            return borrowHistoryService.deleteBatchByHistoryId(ints) > 0 ?
                    packaging(StateCode.SUCCESS, "批量删除借阅历史" + ChangliangUtil.DELETESUCCESS, null)
                    : packaging(StateCode.FAIL, "批量删除借阅历史" + ChangliangUtil.DELETEFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息为空", null);
    }

    /**
     * 通过 historyId 更新资料
     *
     * @param borrowHistory
     * @return
     */
    @PostMapping("/updateBorrowHistoryByHistoryId")
    public State<Object> updateBorrowHistoryByHistoryId(BorrowHistory borrowHistory) {
        if (borrowHistory != null && borrowHistory.getHistoryId() != null && borrowHistory.getHistoryId() > 0) {
            return borrowHistoryService.updateBorrowHistoryByHistoryId(borrowHistory) > 0 ?
                    packaging(StateCode.SUCCESS, "借阅历史" + ChangliangUtil.UPDATESUCCESS, null)
                    : packaging(StateCode.FAIL, "借阅历史" + ChangliangUtil.UPDATEFAIL, null);
        }
        return packaging(StateCode.FAIL, "信息不完整，或请传入BorrowHistoryId", null);
    }

    /**
     * 通过 readerId 和 BookId 更改对应单一借阅历史
     *
     * @param borrowHistory
     * @return
     */
    @PutMapping("/updateBorrowHistoryByReaderIdAndBookId")
    public State<Object> updateBorrowHistoryByReaderIdAndBookId(BorrowHistory borrowHistory) {
        if (borrowHistory != null && borrowHistory.getReaderId() != null && borrowHistory.getReaderId() > 0
                && borrowHistory.getBookId() != null && borrowHistory.getBookId() > 0) {
            return borrowHistoryService.updateBorrowHistoryByReaderIdAndBookId(borrowHistory) > 0 ?
                    packaging(StateCode.SUCCESS, "借阅历史" + ChangliangUtil.UPDATESUCCESS, null)
                    : packaging(StateCode.FAIL, "借阅历史" + ChangliangUtil.UPDATEFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息不完整", null);
    }

    /**
     * 通过 historyId 查出对应记录
     *
     * @param historyId
     * @return
     */
    @GetMapping("/selectBorrowHistoryByHistoryId")
    public State<Object> selectBorrowHistoryByHistoryId(Integer historyId) {
        if (historyId != null && historyId > 0) {
            BorrowHistory borrowHistory = borrowHistoryService.selectBorrowHistoryByHistoryId(historyId);
            return borrowHistory != null ?
                    packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, borrowHistory)
                    : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息不完整", null);
    }

    /**
     * 分页查出 readId 对应全部记录,测试通过
     *
     * @param readerId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectBorrowHistoryByReaderId")
    public State<Object> selectBorrowHistoryByReaderId(Integer readerId, Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            return packaging(StateCode.FAIL, "请输入当前页", null);
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = systemMapper.getPageLine();
        }
        if (readerId != null && readerId > 0) {
            Page<BorrowHistory> pageList = borrowHistoryService.selectBorrowHistoryByReaderId(readerId, currentPage, pageSize);
            return pageList.getList().size() != 0 ?
                    packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, pageList)
                    : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息不完整", null);
    }

    /**
     * 分页查出 bookId 查出对应全部记录，测试通过
     *
     * @param bookId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectBorrowHistoryByBookId")
    public State<Object> selectBorrowHistoryByBookId(Integer bookId, Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            return packaging(StateCode.FAIL, "请输入当前页", null);
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = systemMapper.getPageLine();
        }
        if (bookId != null && bookId > 0) {
            Page<BorrowHistory> pageList = borrowHistoryService.selectBorrowHistoryByBookId(bookId, currentPage, pageSize);
            return pageList.getList().size() != 0 ?
                    packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, pageList)
                    : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
        }
        return packaging(StateCode.FAIL, "传入信息不完整", null);
    }

    /**
     * 查出全部未删除记录，测试通过
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectAllUnDeleteBorrowHistory")
    public State<Object> selectAllUnDeleteBorrowHistory(Integer currentPage, Integer pageSize) {
        if (currentPage == null ) {
            return packaging(StateCode.FAIL, "请输入当前页", null);
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = systemMapper.getPageLine();
        }
        Page<BorrowHistory> pageList = borrowHistoryService.selectAllUnDeleteBorrowHistory(currentPage, pageSize);
        return pageList.getList().size() != 0 ?
                packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, pageList)
                : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
    }

    /**
     * 查出全部记录，测试通过
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectAllBorrowHistory")
    public State<Object> selectAllBorrowHistory(Integer currentPage, Integer pageSize) {
        if (currentPage == null ) {
            return packaging(StateCode.FAIL, "请输入当前页", null);
        }
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = systemMapper.getPageLine();
        }
        Page<BorrowHistory> pageList = borrowHistoryService.selectAllBorrowHistory(currentPage, pageSize);
        return pageList.getList().size() != 0 ?
                packaging(StateCode.SUCCESS, ChangliangUtil.QUERYSUCCESS, pageList)
                : packaging(StateCode.FAIL, ChangliangUtil.QUERYFAIL, null);
    }
}
