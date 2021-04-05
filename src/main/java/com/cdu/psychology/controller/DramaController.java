package com.cdu.psychology.controller;

import com.cdu.psychology.entity.Chapter;
import com.cdu.psychology.entity.Dialogue;
import com.cdu.psychology.entity.Dialogue_score;
import com.cdu.psychology.entity.Show;
import com.cdu.psychology.service.DramaService;
import com.github.pagehelper.PageInfo;
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
                                          @RequestParam(name = "location",required = true) String location,
                                          @RequestParam(name = "character",required = true) String character,
                                          @RequestParam(name = "show_id",required = true) int show_id,
                                          @RequestParam(name = "next",required = true) String next){
        Map<String, Object> data = new HashMap<>();
        String lead = dramaService.getShowLead(show_id);
        if(lead == null){
            data.put("code",413);
            return data;
        }
        Chapter chapter = new Chapter();

        if(next.equals(INIT_ID)){
            chapter.setId(INIT_ID);
        }else{
            chapter.setId(next);
        }
        String nextId = UUID.randomUUID().toString();
        chapter.setNext(nextId);

        chapter.setName(name);
        chapter.setTime(time);
        chapter.setLocation(location);
        chapter.setCharacter(character+lead+";");
        chapter.setShow_id(show_id);
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
                                           @RequestParam(name = "content1",required = false) String content1,
                                           @RequestParam(name = "content2",required = false) String content2,
                                           @RequestParam(name = "content3",required = false) String content3,
                                           @RequestParam(name = "content4",required = false) String content4,
                                           @RequestParam(name = "score1",required = false) int score1,
                                           @RequestParam(name = "score2",required = false) int score2,
                                           @RequestParam(name = "score3",required = false) int score3,
                                           @RequestParam(name = "score4",required = false) int score4){
        Map<String, Object> data = new HashMap<>();
        boolean star = dramaService.getChapterLead(chapter_id).equals(character);
        boolean init = id.equals(INIT_ID);
        if(init){
            //初始化第一个对话
            if(star){
                //第一个是主角
                Dialogue dialogue = new Dialogue();
                dialogue.setId(INIT_ID);
                dialogue.setCharacter(character);
                dialogue.setContent("NULL");
                dialogue.setIs_lead(1);
                dialogue.setChapter_id(chapter_id);
                String Gen_next = UUID.randomUUID().toString();
                dialogue.setNext(Gen_next);

                Dialogue_score ds = new Dialogue_score();
                ds.setContent1(content1);
                ds.setContent2(content2);
                ds.setContent3(content3);
                ds.setContent4(content4);
                ds.setScore1(score1);
                ds.setScore2(score2);
                ds.setScore3(score3);
                ds.setScore4(score4);
                ds.setChapter_id(chapter_id);
                if(dramaService.putDialogue_score(ds)==0){
                    data.put("code",413);
                    return data;
                }
                dialogue.setFour_content(ds.getId());
                if(dramaService.putDialogue(dialogue)==0){
                    data.put("code",413);
                    return data;
                }
                data.put("code",200);
                data.put("next",Gen_next);
                return data;
            }else {
                //第一个不是主角
                Dialogue dialogue = new Dialogue();
                dialogue.setId(INIT_ID);
                dialogue.setCharacter(character);
                dialogue.setContent(content);
                dialogue.setIs_lead(0);
                dialogue.setChapter_id(chapter_id);
                String Gen_next = UUID.randomUUID().toString();
                dialogue.setNext(Gen_next);
                if(dramaService.putDialogue(dialogue)==0){
                    data.put("code",413);
                    return data;
                }
                data.put("code",200);
                data.put("next",Gen_next);
                return data;
            }
        }else{
            //不是第一个对话
            if(star){
                //是主角
                Dialogue dialogue = new Dialogue();
                dialogue.setId(id);
                dialogue.setCharacter(character);
                dialogue.setContent("NULL");
                dialogue.setIs_lead(1);
                dialogue.setChapter_id(chapter_id);
                String Gen_next = UUID.randomUUID().toString();
                dialogue.setNext(Gen_next);

                Dialogue_score ds = new Dialogue_score();
                ds.setContent1(content1);
                ds.setContent2(content2);
                ds.setContent3(content3);
                ds.setContent4(content4);
                ds.setScore1(score1);
                ds.setScore2(score2);
                ds.setScore3(score3);
                ds.setScore4(score4);
                ds.setChapter_id(chapter_id);
                if(dramaService.putDialogue_score(ds)==0){
                    data.put("code",413);
                    return data;
                }
                dialogue.setFour_content(ds.getId());
                if(dramaService.putDialogue(dialogue)==0){
                    data.put("code",413);
                    return data;
                }
                data.put("code",200);
                data.put("next",Gen_next);
                return data;
            }else {
                //也不是主角
                Dialogue dialogue = new Dialogue();
                dialogue.setId(id);
                dialogue.setCharacter(character);
                dialogue.setContent(content);
                dialogue.setIs_lead(0);
                dialogue.setChapter_id(chapter_id);
                String Gen_next = UUID.randomUUID().toString();
                dialogue.setNext(Gen_next);
                if(dramaService.putDialogue(dialogue)==0){
                    data.put("code",413);
                    return data;
                }
                data.put("code",200);
                data.put("next",Gen_next);
                return data;
            }
        }
    }

    @GetMapping("/list")
    public Map<String, Object> getShowList(@RequestParam(name = "page",required = false,defaultValue = "1") int page,
                                           @RequestParam(name = "size",required = false,defaultValue = "10") int size){
        Map<String, Object> data = new HashMap<>();
        PageInfo<Show>  showPageInfo = dramaService.findAllShowByPageS(page,size);
        data.put("code",200);
        data.put("data",showPageInfo);
        return data;
    }

    @GetMapping("/{drama_id}/chapter/{chapter_id}")
    public Map<String, Object> getChapter(@PathVariable(name = "drama_id", required = true)String drama_id,
                                          @PathVariable(name = "chapter_id", required = true)String chapter_id){
        Map<String, Object> data = new HashMap<>();
        Chapter chapter = dramaService.getChapter(drama_id, chapter_id);
        data.put("code",200);
        data.put("data",chapter);
        return data;
    }
    @GetMapping("/{drama_id}/chapter/{chapter_id}/{dialogue_id}")
    public Map<String, Object> getDialogue(@PathVariable(name = "dialogue_id", required = true)String dialogue_id){
        Map<String, Object> data = new HashMap<>();
        Dialogue dialogue = dramaService.getDialogueInChapter(dialogue_id);
        if(dialogue.getIs_lead()==0){
            data.put("code",200);
            data.put("data",dialogue);
            return data;
        }else {
            Map<String, Object> con = new HashMap<>();
            Dialogue_score ds = dramaService.getDialogue_socreInChapter(dialogue.getFour_content());
            con.put("id",dialogue.getId());
            con.put("character",dialogue.getCharacter());
            con.put("content",dialogue.getContent());
            con.put("is_lead",dialogue.getIs_lead());
            con.put("four_content",dialogue.getFour_content());
            con.put("chapter_id",dialogue.getFour_content());
            con.put("next",dialogue.getNext());
            con.put("content1",ds.getContent1());
            con.put("content2",ds.getContent2());
            con.put("content3",ds.getContent3());
            con.put("content4",ds.getContent4());
            con.put("score1",ds.getScore1());
            con.put("score2",ds.getScore2());
            con.put("score3",ds.getScore3());
            con.put("score4",ds.getScore4());
            data.put("code",200);
            data.put("data",con);
            return data;
        }
    }
}
