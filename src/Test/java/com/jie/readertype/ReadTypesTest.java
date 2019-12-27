package com.jie.readertype;

import com.qf.config.TestConfig;
import com.qf.entity.ReaderTypes;
import com.qf.service.IReaderTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author: JunJie
 * @classBelongTo: library.com.jie.readertype
 * @description: TODO
 * @createTime: 2019/12/23 11:59
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
@Configuration
public class ReadTypesTest {
    @Autowired
    IReaderTypeService readerTypeServiceImpl;

    @Test
    public void insertTest() {
        ReaderTypes readerTypes = new ReaderTypes();
        readerTypes.setReaderTypeName("VIP");
        readerTypes.setBorrowDay((byte) 12);
        readerTypes.setBorrowNumber((byte) 21);
        readerTypes.setContinueNumber((byte) 2);
        readerTypes.setDeposit((float) 100);
        Integer integer = readerTypeServiceImpl.insertReaderTypes(readerTypes);
        System.out.println("-------------------");
        System.out.println("insert:" + integer);
        System.out.println("-------------------");
    }

    @Test
    public void deleteTest() {
//        Integer integer = readerTypeServiceImpl.deleteReaderTypesByReaderTypeId(1);
        Integer integer = readerTypeServiceImpl.deleteReaderTypesByReaderTypeName("VIP");
        System.out.println("-------------------");
        System.out.println("delete:" + integer);
        System.out.println("-------------------");
    }

    @Test
    public void updateTest() {
        ReaderTypes readerTypes = new ReaderTypes();
        readerTypes.setIsDelete(1);
        readerTypes.setReaderTypeId(1);
        Integer integer = readerTypeServiceImpl.updateReaderTypesByReadTypeId(readerTypes);
        System.out.println("-------------------");
        System.out.println("update:" + integer);
        System.out.println("-------------------");
    }


}
