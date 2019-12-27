package com.jie.borrowhistory;

import com.qf.config.TestConfig;
import com.qf.entity.BorrowHistory;
import com.qf.service.IBorrowHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * @author: JunJie
 * @classBelongTo: library.com.jie.borrowhistory
 * @description: TODO
 * @createTime: 2019/12/23 13:50
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
@Configuration
public class BorrowHistoryTest {
    @Autowired
    IBorrowHistoryService borrowHistoryServiceImpl;

    @Test
    public void insertTest() {
        BorrowHistory borrowHistory = new BorrowHistory();
        borrowHistory.setReaderId(1);
        borrowHistory.setBorrowTime(new Date());
        borrowHistory.setRenew((byte) 0);
        borrowHistory.setOverdue((byte) 0);
        borrowHistory.setFine((float) 0);
        borrowHistory.setBookId(1);

        System.out.println("-----------------");
        System.out.println("insert:" + borrowHistoryServiceImpl.insertBorrowHistory(borrowHistory));
        System.out.println("-----------------");
    }

    @Test
    public void deleteTest() {
        System.out.println("-----------------");
        System.out.println("delete:" + borrowHistoryServiceImpl.deleteBorrowHistoryByBookId(1));
        System.out.println("-----------------");
    }

    @Test
    public void updateTest() {
        BorrowHistory borrowHistory = new BorrowHistory();
        borrowHistory.setHistoryId(1);
        borrowHistory.setBookId(1);
        borrowHistory.setIsDelete(1);
        borrowHistory.setFine((float) 7.81);
        System.out.println("---------------------");
        System.out.println("update:"+borrowHistoryServiceImpl.updateBorrowHistoryByHistoryId(borrowHistory));
        System.out.println("---------------------");
    }
}
