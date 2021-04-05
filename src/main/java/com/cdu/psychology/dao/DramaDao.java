package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Chapter;
import com.cdu.psychology.entity.Dialogue;
import com.cdu.psychology.entity.Dialogue_score;
import com.cdu.psychology.entity.Show;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DramaDao {

    @Insert("insert into psychology.show (name,introduce,image,show.character,lead) values (#{name},#{introduce},#{image},#{character},#{lead})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int putShow(Show show);

    @Select("select lead from psychology.show where id=#{id}")
    String getShowLead(int id);

    @Insert("insert into psychology.chapter (id,name,time,location,chapter.character,show_id,next) values (#{id},#{name},#{time},#{location},#{character},#{show_id},#{next})")
    @Options(keyProperty = "id")
    int putChapter(Chapter chapter);

    @Select("select chapter.character from chapter where id = #{id}")
    String getCharacterInChapter(String id);

    @Insert("insert into dialogue_score (content1,score1,content2,score2,content3,score3,content4,score4,chapter_id) " +
            "values (#{content1},#{score1},#{content2},#{score2},#{content3},#{score3},#{content4},#{score4},#{chapter_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int putDialogue_score(Dialogue_score ds);

    @Insert("insert into dialogue (id,dialogue.character,content,is_lead,four_content,chapter_id,next) values " +
            "(#{id},#{character},#{content},#{is_lead},#{four_content},#{chapter_id},#{next})")
    int putDialouge(Dialogue d);

    @Select("select * from psychology.show")
    List<Show> getShows();

    @Select("select * from psychology.chapter where show_id = #{id}")
    List<Chapter> getChapterInShow(int id);

    @Select("select * from chapter where show_id=#{show_id} and id = #{chapter_id}")
    Chapter getChapter(String show_id,String chapter_id);

    @Select("select * from dialogue where chapter_id = #{chapter_id}")
    List<Dialogue> getDialogueInChapter(String chapter_id);

    Dialogue getDialogueById(String id);

    Dialogue_score getDialogue_scoreInChapter(int id);

}
