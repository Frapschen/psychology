package com.cdu.psychology.dao;

import com.cdu.psychology.entity.Code;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommonDao {
    int setCode(String code);
    Code getCode(String code);
    int upateCode(String code);

}
