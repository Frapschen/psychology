package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Chapter;
import com.cdu.psychology.entity.Dialogue;
import com.cdu.psychology.entity.Dialogue_score;
import com.cdu.psychology.entity.Show;
import com.cdu.psychology.service.DramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/drama")
public class DramaController {

    private static final String INIT_ID= "00000000-0000-0000-0000-000000000000";

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
    @PutMapping("/chapter")
    public Map<String, Object> putChapter(@RequestParam(name = "name",required = true) String name,
                                          @RequestParam(name = "time",required = true) String time,
                                          @RequestParam(name = "localtion",required = true) String localtion,
                                          @RequestParam(name = "character",required = true) String character,
                                          @RequestParam(name = "show_id",required = true) int show_id){
        Map<String, Object> data = new HashMap<>();
        String lead = dramaService.getShowLead(show_id);
        if(lead == null){
            data.put("code",413);
            return data;
        }
        Chapter chapter = new Chapter();
        String Thisid = UUID.randomUUID().toString();
        String nextId = UUID.randomUUID().toString();
        chapter.setId(Thisid);
        chapter.setName(name);
        chapter.setTime(time);
        chapter.setLocation(localtion);
        chapter.setCharacter(character+";"+lead);
        chapter.setShow_id(show_id);
        chapter.setNext(nextId);
        if(dramaService.putChapter(chapter)==0){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        data.put("next_id",nextId);
        return data;
    }
    @GetMapping("/chapter/{chapter_id}/characters")
    public Map<String, Object> getCharacterInChpater(@PathVariable(name = "chapter_id", required = true)String chapter_id){
        Map<String, Object> data = new HashMap<>();
        String [] characters = dramaService.getCharacterInChpater(chapter_id);
        if(characters.length==0){
            data.put("code",413);
            return data;
        }
        data.put("code",200);
        data.put("characters",characters);
        return data;

    }

    @PutMapping("/dialogue")
    public Map<String, Object> putDialogue(@RequestParam(name = "id",required = true) String id,
                                           @RequestParam(name = "character",required = true) String character,
                                           @RequestParam(name = "content",required = false) String content,
                                           @RequestParam(name = "chapter_id",required = true) String chapter_id,
                                           @RequestParam(name = "is_lead",required = true) int is_lead,
                                           @RequestParam(name = "next",required = true) String next,
                                           @RequestParam(name = "content1",required = false) String content1,
                                           @RequestParam(name = "content2",required = false) String content2,
                                           @RequestParam(name = "content3",required = false) String content3,
                                           @RequestParam(name = "content4",required = false) String content4,
                                           @RequestParam(name = "score1",required = false) int score1,
                                           @RequestParam(name = "score2",required = false) int score2,
                                           @RequestParam(name = "score3",required = false) int score3,
                                           @RequestParam(name = "score4",required = false) int score4){
        Map<String, Object> data = new HashMap<>();
        String Gen_next = UUID.randomUUID().toString();
        Dialogue dialogue = new Dialogue();
        //串接数据
        if(id.equals(INIT_ID)){
            dialogue.setId(INIT_ID);
        } else {
            dialogue.setId(next);
        }
        dialogue.setNext(Gen_next);

        dialogue.setChapter_id(chapter_id);
        dialogue.setCharacter(character);

        //处理对话 主角对话
        if(dramaService.getChapterLead(chapter_id).equals(character)){
            dialogue.setIs_lead(1);
            Dialogue_score ds = new Dialogue_score();
            ds.setId(next);
            ds.setContent1(content1);
            ds.setContent2(content2);
            ds.setContent3(content3);
            ds.setContent4(content4);
            ds.setScore1(score1);
            ds.setScore2(score2);
            ds.setScore3(score3);
            ds.setScore4(score4);
            ds.setChapter_id(chapter_id);
            ds.setNext(Gen_next);
            if(dramaService.putDialogue_score(ds)==0){
                data.put("code",413);
                return data;
            }
        }
        dialogue.setContent(content);

        data.put("code",200);
        return data;
    }
}
