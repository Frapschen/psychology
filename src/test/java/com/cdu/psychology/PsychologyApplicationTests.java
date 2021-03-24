package com.cdu.psychology;

import com.cdu.psychology.controller.ArticleControllerTest;
import com.cdu.psychology.controller.CommentControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PsychologyApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void articleControllerTest(){
        new ArticleControllerTest().testAll();
        new CommentControllerTest().testAll();
    }

}
