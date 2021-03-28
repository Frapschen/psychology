package com.cdu.psychology.service;

import com.cdu.psychology.entity.Code;

import java.awt.image.BufferedImage;

public interface CommonService {
    BufferedImage code();
    int checkCode(String code);
}
