package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.entity.System;
import com.qf.entity.SystemExample;
import com.qf.mapper.SystemMapper;
import com.qf.service.ISystemService;
import com.qf.util.Page;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class SystemServiceImpl implements ISystemService {

    @Autowired
    SystemMapper systemMapper;

    @Override
    public Integer insertSystem(System system) {
        int i = systemMapper.insertSelective(system);
        return i;
    }

    //修改系统表
    @Override
    public Integer updateSystem(String picName,System system) {
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andSysIdEqualTo(1);
        system.setSysImg(picName);
        int i = systemMapper.updateByExampleSelective(system, systemExample);
        return i;
    }
   /*@Override
    public Integer updateSystem(System system) {
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andSysIdEqualTo(1);
        int i = systemMapper.updateByExampleSelective(system, systemExample);
        return i;
    }*/

    //查询系统表
    /*@Override
    public List<System> selectSystemList() {
        SystemExample systemExample = new SystemExample();
        systemExample.createCriteria().andIsDeleteEqualTo(1);
        return systemMapper.selectByExample(systemExample);
    }*/

    @Override
    public Page<System> selectSystemList(Integer currentPage, Integer pageSize) {
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = systemMapper.getPageLine();
        }
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage,pageSize);
        SystemExample systemExample = new SystemExample();
        systemExample.createCriteria().andIsDeleteEqualTo(1);
        List<System> systemList = systemMapper.selectByExample(systemExample);
        int totalCount = systemMapper.countByExample(systemExample);

        Page<System> page = new Page<>(currentPage, pageSize, totalCount);
        page.setList(systemList);
        return page;
    }

    @Override
    public Integer updateSystemNoImg(System system) {
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andSysIdEqualTo(1);
        int i = systemMapper.updateByExampleSelective(system, systemExample);
        return i;
    }

//    @Override
//    public Page<System> selectSystemList(Integer currentPage, Integer pageSize) {
//        if (StringUtils.isEmpty(pageSize)) {
//            pageSize = systemMapper.getPageLine();
//        }
//        PageHelper.startPage(currentPage, pageSize);
//        SystemExample systemExample = new SystemExample();
//        systemExample.createCriteria().andSysIdEqualTo(1).andIsDeleteEqualTo(1);
//        List<System> systems = systemMapper.selectByExample(systemExample);
//        int systemNum = systemMapper.countByExample(systemExample);
//        Page<System> pageData = new Page<>(currentPage, pageSize, systemNum);
//        pageData.setList(systems);
//        return pageData;
//    }


}
