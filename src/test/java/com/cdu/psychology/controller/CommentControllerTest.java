package com.cdu.psychology.controller;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CommentControllerTest {
    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public void testAll(){
        putCommentTest();
        deleteCommentTest();
    }
    private void putCommentTest(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"contxt\":\"contxt\"\r\n\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8089/psychology/v1/comment/1/2")
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
    private void deleteCommentTest(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://localhost:8089/psychology/v1/comment/1")
                .method("DELETE", body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            logger.info(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
