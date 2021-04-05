package com.cdu.psychology.service;

import com.cdu.psychology.entity.*;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DramaService {
    int putShow(Show show);
    String getShowLead(int id);
    int putChapter(Chapter chapter);
    String[] getCharacterInChpater(String id);
    String getChapterLead(String id);
    int putDialogue_score(Dialogue_score ds);
    int putDialogue(Dialogue dialogue);
    PageInfo<Show> findAllShowByPageS(int pageNum, int pageSize);
    Chapter getChapter(String show_id,String chapter_id);
    Dialogue getDialogueInChapter(String dialogue_id);
    Dialogue_score getDialogue_socreInChapter(int dialogue_score_id);
}
