package com.qa.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostAPITest extends TestBase {

    TestBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResponse;

    @BeforeMethod
    public void setUp(){
        testBase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        // https://reqres.in/api/users
        url = serviceUrl + apiUrl;
    }

    @Test
    public void postApiTest() throws IOException {
        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        //jackson API - for marshalling/unmarshalling
        ObjectMapper mapper = new ObjectMapper();
        Users user = new Users("Arjun", "Engineer");

        //object to JSON file
        mapper.writeValue(new File("C:\\Users\\SMART USER\\IdeaProjects\\restAPI\\src\\main\\java\\com\\qa\\data\\users.json"), user);

        //object to json in string
        String usersJsonString = mapper.writeValueAsString(user);
        System.out.println(usersJsonString);

        httpResponse = restClient.post(url, usersJsonString,headerMap);

        //1. status code:
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);

        //2. json string
        String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");


        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("The response from API is :"+ responseJson);

        Users userObj = mapper.readValue(responseString, Users.class);
        System.out.println(userObj);

        Assert.assertEquals(user.getName(), userObj.getName());
        Assert.assertEquals(user.getJob(), userObj.getJob());

        System.out.println(userObj.getId());
        System.out.println(userObj.getCreatedAt());
    }













}
