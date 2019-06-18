package com.training.task.module11.tests;

import com.training.task.module11.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RestTemplateTest {

    @Test
    public void checkStatusCode() {
        RestTemplate restTempl = new RestTemplate();
        ResponseEntity<User[]> response = restTempl.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        int statusCode = response.getStatusCodeValue();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void checkResponseHeader() {
        RestTemplate restTempl = new RestTemplate();
        ResponseEntity<User[]> response = restTempl.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        List<String> contentType = response.getHeaders().get("content-type");
        Assert.assertTrue(contentType.get(0).contains("application/json; charset=utf-8"));
    }

    @Test
    public void checkResponseBody() {
        RestTemplate restTempl = new RestTemplate();
        ResponseEntity<User[]> response = restTempl.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        Assert.assertEquals(response.getBody().length, 10);
    }

    @Test
    public void checkCompanyName() {
        RestTemplate restTempl = new RestTemplate();
        ResponseEntity<User[]> response = restTempl.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
        Assert.assertEquals(response.getBody()[0].getCompany().getName(), "Romaguera-Crona");
    }

}
