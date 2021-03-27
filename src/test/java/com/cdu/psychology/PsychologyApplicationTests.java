package com.cdu.psychology;

import com.cdu.psychology.Dao.MybatisTest;
import com.cdu.psychology.controller.ArticleControllerTest;
import com.cdu.psychology.controller.CommentControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PsychologyApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void articleControllerTest(){
        new ArticleControllerTest().testAll();
        new CommentControllerTest().testAll();
    }
    @Test
    void mybatisTest(){
        new MybatisTest().connectTest();
    }

}
