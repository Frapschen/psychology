package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.CommonDao;
import com.cdu.psychology.entity.Code;
import com.cdu.psychology.service.CommonService;
import com.cdu.psychology.utils.GraphicHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service()
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonDao commonDao;
    @Override
    public BufferedImage code() {
        String code = null;
        final int width = 180; // 图片宽度
        final int height = 40; // 图片高度
        final String imgType = "jpeg"; // 指定图片格式 (不是指MIME类型)
        BufferedImage bufferedImage =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        code = GraphicHelper.create(width,height,imgType,bufferedImage);
        int status = commonDao.setCode(code);
        if(status != 1){
            return null;
        }
        //save code in mysql
        return bufferedImage;
    }

    @Override
    public int checkCode(String code) {
        Code i = commonDao.getCode(code);
        if(i!=null){
            commonDao.upateCode(code);
            return 1;
        }
        return 0;
    }
}
