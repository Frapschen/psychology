package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.DramaDao;
import com.cdu.psychology.entity.*;
import com.cdu.psychology.service.DramaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DramaServceImple implements DramaService {

    @Autowired
    private DramaDao dramaDao;
    @Override
    public int putShow(Show show) {
        return dramaDao.putShow(show);
    }

    @Override
    public String getShowLead(int id) {
        return dramaDao.getShowLead(id);
    }

    @Override
    public int putChapter(Chapter chapter) {
        return dramaDao.putChapter(chapter);
    }

    @Override
    public String[] getCharacterInChpater(String id) {
        String character = dramaDao.getCharacterInChapter(id);
        String[] characters = character.split(";");
        return characters;
    }

    @Override
    public String getChapterLead(String id) {
        String [] c = dramaDao.getCharacterInChapter(id).split(";");
        if(c.length==0){
            return "";
        }
        return c[c.length-1];

    }

    @Override
    public int putDialogue_score(Dialogue_score ds) {
        return dramaDao.putDialogue_score(ds);
    }

    @Override
    public int putDialogue(Dialogue dialogue) {
        return dramaDao.putDialouge(dialogue);
    }

    @Override
    public PageInfo<Show> findAllShowByPageS(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Show> showList  = dramaDao.getShows();
        PageInfo<Show> pageInfo = new PageInfo<Show>(showList);
        for(Show s:pageInfo.getList()){
            List<Chapter> chapters = dramaDao.getChapterInShow(s.getId());
            Map<String,String> m = new HashMap<>();
            for(Chapter c : chapters){
                m.put(c.getId(),c.getNext());
            }
            List<String> strings =  new ArrayList();
            strings.add("00000000-0000-0000-0000-000000000000");
            getl(m,"00000000-0000-0000-0000-000000000000",strings);
            s.setChapters(strings);
        }
        return pageInfo;
    }

    @Override
    public Chapter getChapter(String show_id, String chapter_id) {
        Chapter chapter = dramaDao.getChapter(show_id,chapter_id);
        List<Dialogue> dialogues = dramaDao.getDialogueInChapter(chapter_id);
        Map<String,String> m = new HashMap<>();
        for(Dialogue d : dialogues){
            m.put(d.getId(),d.getNext());
        }
        List<String> strings =  new ArrayList();
        strings.add("00000000-0000-0000-0000-000000000000");
        getl(m,"00000000-0000-0000-0000-000000000000",strings);
        chapter.setDialogue(strings);
        return chapter;
    }

    @Override
    public Dialogue getDialogueInChapter(String dialogue_id) {
        return dramaDao.getDialogueById(dialogue_id);
    }

    @Override
    public Dialogue_score getDialogue_socreInChapter(int dialogue_score_id) {
        return dramaDao.getDialogue_scoreInChapter(dialogue_score_id);
    }

    private void getl(Map<String,String> m, String target,List<String> strings){
        String v = null;
        for(String key : m.keySet()){
            if(key.equals(target)){
                if(m.size()==1){
                    m.remove(key);
                    break;
                }
                v = m.get(key);
                strings.add(v);
                m.remove(key);
                break;
            }
        }
        if(m.size()>0){
            getl(m,v,strings);
        }
        return;
    }
}
