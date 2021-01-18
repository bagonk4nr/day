package com.web.day.services;

import com.web.day.config.ApiUrl;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

public class FindURL {

    @Async
    public String findUrls(String urlFind) throws IOException {
//        HttpPost httpPost = new HttpPost(ApiUrl.webApiFindurl);
        HttpPost httpPost = new HttpPost(ApiUrl.webHApiFindurl); // For Hapi.js Framework
        String json = "{\"url\": " + "\"" + urlFind + "\"" + " }";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());;
        System.out.println(result);
        return result;
    }

}
