package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;


import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

    TestBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse httpResopnse;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException {
        testBase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");
        // https://reqres.in/api/users

        url = serviceUrl + apiUrl;

    }

    @Test(priority = 1)
    public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
        restClient = new RestClient();
        httpResopnse = restClient.get(url);

        // a. Status code
        int statusCode = httpResopnse.getStatusLine().getStatusCode();
        System.out.println("Status code is " + statusCode);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not as expected(200)");

        // b. Json String
        String responseString = EntityUtils.toString(httpResopnse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON : " + responseJson);
        //total value assertion
        String total = TestUtil.getValueByJPath(responseJson, "/total");
        System.out.println("Value of total values is :" + total);
        Assert.assertEquals(Integer.parseInt(total), 12);

        //get the value from JSON Array
        String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
        String last_name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
        String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
        String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");

        System.out.println("lastname : " + last_name + "\nfirstname : " + first_name + "\nid : " + id + "\navatar : " + avatar);

        // c. All Headers
        Header[] headersArray = httpResopnse.getAllHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();

        for (Header header : headersArray) allHeaders.put(header.getName(), header.getValue());

        System.out.println("All Headers --> " + allHeaders);
    }

    @Test(priority = 2)
    public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
        restClient = new RestClient();

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        httpResopnse = restClient.get(url, headerMap);


        // a. Status code
        int statusCode = httpResopnse.getStatusLine().getStatusCode();
        System.out.println("Status code is " + statusCode);
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not as expected(200)");

        // b. Json String
        String responseString = EntityUtils.toString(httpResopnse.getEntity(), "UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON : " + responseJson);
        //total value assertion
        String total = TestUtil.getValueByJPath(responseJson, "/total");
        System.out.println("Value of total values is :" + total);
        Assert.assertEquals(Integer.parseInt(total), 12);

        //get the value from JSON Array
        String first_name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
        String last_name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
        String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
        String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");

        System.out.println("lastname : " + last_name + "\nfirstname : " + first_name + "\nid : " + id + "\navatar : " + avatar);

        // c. All Headers
        Header[] headersArray = httpResopnse.getAllHeaders();

        HashMap<String, String> allHeaders = new HashMap<String, String>();

        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }

        System.out.println("All Headers --> " + allHeaders);
    }

}
