package com.cdu.psychology;

import com.cdu.psychology.controller.WebsocketController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.cdu.psychology.dao")
@EnableTransactionManagement
public class PsychologyApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PsychologyApplication.class);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        WebsocketController.setApplicationContext(configurableApplicationContext);
    }
}
