package com.qf.service;

import com.qf.entity.Costs;
import com.qf.entity.System;
import com.qf.util.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISystemService {
    Integer insertSystem(System system);

    //修改系统文件
    Integer updateSystem(String picName,System system);

    //查询系统
    Page<System> selectSystemList(Integer currentPage, Integer pageSize);

    //修改系统配置不更改图片
    Integer updateSystemNoImg(System system);
//    Page<System> selectSystemList(Integer currentPage, Integer pageSize);

}
