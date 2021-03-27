package com.cdu.psychology;
import com.cdu.psychology.controller.ArticleControllerTest;
import com.cdu.psychology.controller.CommentControllerTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
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
