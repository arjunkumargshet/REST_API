package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient {

    // 1. GET method without Headers
    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);// http get request
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);// hit the GET URL
        return httpResponse;

    }

    // 1. GET method with Headers
    public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws  IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);// http get request

        for(Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse httpResopnse = httpClient.execute(httpGet);// hit the GET URL
        return httpResopnse;

    }


}
