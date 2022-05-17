package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient {

    CloseableHttpResponse httpResponse;
    // 1. GET method without Headers
    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);// http get request
        httpResponse = httpClient.execute(httpGet);// hit the GET URL
        return httpResponse;

    }

    // 2. GET method with Headers
    public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws  IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);// http get request
        for(Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        httpResponse = httpClient.execute(httpGet);// hit the GET URL
        return httpResponse;

    }

    //3. POST method
    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);//http POST request
        httpPost.setEntity(new StringEntity(entityString));

        //for headers
        for(Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        httpResponse = httpClient.execute(httpPost);
        return httpResponse;
    }


}
