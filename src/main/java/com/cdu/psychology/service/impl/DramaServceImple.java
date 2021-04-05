package com.cdu.psychology.service.impl;

import com.cdu.psychology.dao.DramaDao;
import com.cdu.psychology.entity.Chapter;
import com.cdu.psychology.entity.Dialogue_score;
import com.cdu.psychology.entity.Show;
import com.cdu.psychology.service.DramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return 0;
    }
}
