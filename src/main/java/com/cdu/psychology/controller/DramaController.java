package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Show;
import com.cdu.psychology.service.DramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/drama")
public class DramaController {

    @Autowired
    private DramaService dramaService;
    @PutMapping("/show")
    public Map<String, Object> putShow(@RequestParam(name = "name",required = true) String name,
                                       @RequestParam(name = "introduce",required = true) String introduce,
                                       @RequestParam(name = "image",required = true) MultipartFile image,
                                       @RequestParam(name = "character",required = true) String character,
                                       @RequestParam(name = "lead",required = true) String lead) throws IOException {
        Map<String, Object> data = new HashMap<>();
        Show show = new Show();
        show.setName(name);
        show.setIntroduce(introduce);
        if(image!=null){
            String base64Str = Base64.getEncoder().encodeToString(image.getBytes());
            show.setImage(base64Str);
        }
        if(character.split(";").length==0){
            data.put("code",413);
            return data;
        }
        show.setCharacter(character);
        show.setLead(lead);
        if(dramaService.putShow(show)==0){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        data.put("show_id",show.getId());
        return data;
    }
    public Map<String, Object> putChapter(@RequestParam(name = "name",required = true) String name,
                                          @RequestParam(name = "time",required = true) String introduce,
                                          @RequestParam(name = "localtion",required = true) MultipartFile image,
                                          @RequestParam(name = "character",required = true) String character,
                                          @RequestParam(name = "show_id",required = true) int show_id){

    }
}
