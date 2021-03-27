package com.cdu.psychology;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cdu.psychology.dao")
public class PsychologyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsychologyApplication.class, args);
    }

}
