package com.cdu.psychology.controller;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ArticleControllerTest {
    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public void testAll(){
        getArticlesListTest();
        putArticleTest();
        postArticleTest();
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
    private void putArticleTest(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\":\"name\",\r\n    \"introduce\":\"introduce\",\r\n    \"context\":\"context\",\r\n    \"create_time\":\"create_time\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8089/psychology/v1/article")
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            logger.info(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void postArticleTest(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\":\"name\",\r\n    \"introduce\":\"introduce\",\r\n    \"context\":\"context\",\r\n    \"create_time\":\"create_time\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8089/psychology/v1/article")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            logger.info(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
