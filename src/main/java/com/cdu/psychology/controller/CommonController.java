package com.cdu.psychology.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/common")
public class CommonController {
    @GetMapping(value = "/code",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public BufferedImage getCode(){
//        try {
//            return ImageIO.read(new FileInputStream(new File("D:/test.jpg")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
