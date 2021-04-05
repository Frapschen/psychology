package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Chapter;
import com.cdu.psychology.entity.Dialogue_score;
import com.cdu.psychology.entity.Show;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

    @Insert("insert into dialogue_score (id,content1,score1,content2,score2,content3,score3,content4,score4,chapter_id) " +
            "values (#{id},#{content1},#{score1},#{content2},#{score2},#{content3},#{score3},#{content4},#{score4},#{chapter_id})")
    @Options(keyProperty = "id")
    int putDialogue_score(Dialogue_score ds);

}
