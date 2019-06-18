package com.training.task.module11.tests;

import com.training.task.module11.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<String> companyNames = Stream.of(response.getBody()).map(user -> user.getCompany().getName()).collect(Collectors.toList());
        Assert.assertTrue(companyNames.contains("Romaguera-Crona"));
    }

    @Test
    public void createUser() {
        RestTemplate restTempl = new RestTemplate();
        User user = new User("testuser");
        HttpEntity<User> request = new HttpEntity<User>(user);
        ResponseEntity<User> response = restTempl.exchange("https://jsonplaceholder.typicode.com/users", HttpMethod.POST, request, User.class);
        Assert.assertEquals(response.getBody().getName(), user.getName());
    }
}
