package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Show;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DramaDao {

    @Insert("insert into psychology.show (name,introduce,image,show.character,lead) values (#{name},#{introduce},#{image},#{character},#{lead})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int putShow(Show show);
}
