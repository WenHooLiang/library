package com.jie.borrows;

import com.qf.config.TestConfig;
import com.qf.entity.Borrows;
import com.qf.service.IBorrowsService;
import com.qf.util.Page;
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
 * @classBelongTo: library.com.jie.borrows
 * @description: TODO
 * @createTime: 2019/12/21 17:02
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
@Configuration
public class BorrowsTest {
    @Autowired
    IBorrowsService borrowsServiceImpl;

    @Test
    public void selectTest() {
        Page<Borrows> page = borrowsServiceImpl.selectAllBorrows(1, 4);

//        Integer integer = borrowsServiceImpl.selectCountAllUnDeleteBorrows();

//        Borrows borrows = borrowsServiceImpl.selectBorrowsByBookStateId(2);
        System.out.println("-------------------------------");
//        System.out.println(borrowsList.size());
//        System.out.println("数量：" + integer);
//        System.out.println(borrows);
        System.out.println(page.getList().get(0));

        System.out.println("------ -------------------------");
    }

    @Test
    public void updateTest(){
        Borrows borrows = new Borrows();
        borrows.setFine((float) 9.8);
        borrows.setBorrowId(1);
        borrows.setBorrowTime(new Date());
        System.out.println("----------------------");
        System.out.println(borrowsServiceImpl.updateBorrowsByBorrowId(borrows));
        System.out.println("----------------------");
    }

    @Test
    public void checkTest(){
        System.out.println(borrowsServiceImpl.haveBooks(1));
    }
}
