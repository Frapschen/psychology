package com.cdu.psychology.controller;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ArticleControllerTest {
    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public void testAll(){
        getArticlesListTest();
    }
    private void getArticlesListTest(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8089/psychology/v1/article/list")
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            logger.info(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
