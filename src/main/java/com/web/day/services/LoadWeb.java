package com.web.day.services;

import com.web.day.config.ApiUrl;
import com.web.day.libs.ModelHTML;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.Map;

public class LoadWeb {

    @Async
    public Map<String, Object> loadMenu() throws IOException, JSONException {

//        HttpPost httpPost = new HttpPost(ApiUrl.webApiMenu);
        HttpPost httpPost = new HttpPost(ApiUrl.webHApiMenu); // For Hapi.js Framework

        String json = "{\"url\": \"\"}";
        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);

        String result = EntityUtils.toString(response.getEntity());;
        System.out.println(result);

        ModelHTML convert = new ModelHTML();
        return convert.convertData(result, "menu");

    }
}
