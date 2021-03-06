package com.qf.controller;

import com.qf.config.ImgesConfig;
import com.qf.constan.StateCode;
import com.qf.controller.base.Base;
import com.qf.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * @program: library
 * @description:图片..文件上传
 * @author: XiongJun
 * @create: 2019-12-22 20:28
 **/
@RestController
public class FileContorller extends Base {

    @Autowired
    ImgesConfig imagePath;

    /**
     * 文件上传
     */
    @PostMapping("/fileUpload")
    public Object upload(@RequestParam("fileName") MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath(imagePath.getPath());
        String upload = FileUtils.upload(file, path, file.getOriginalFilename());
        if(StringUtils.isEmpty(upload)){
            return packaging(StateCode.FAIL,"上传失败",null);
        }else{
            return packaging(StateCode.SUCCESS,"上传成功",imagePath.getImagePath()+upload);
        }
    }
}
