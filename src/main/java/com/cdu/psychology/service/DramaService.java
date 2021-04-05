package com.cdu.psychology.service;

import com.cdu.psychology.entity.Chapter;
import com.cdu.psychology.entity.Dialogue_score;
import com.cdu.psychology.entity.Show;

import java.util.List;

public interface DramaService {
    int putShow(Show show);
    String getShowLead(int id);
    int putChapter(Chapter chapter);
    String[] getCharacterInChpater(String id);
    String getChapterLead(String id);
    int putDialogue_score(Dialogue_score ds);

}
