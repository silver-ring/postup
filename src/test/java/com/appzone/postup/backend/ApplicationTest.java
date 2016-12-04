/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appzone.postup.backend;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author hp
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@ActiveProfiles("development")
public class ApplicationTest  extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testApproveComment() {
        
        HttpHeaders headers = new HttpHeaders();
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("text", "test");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange("/comment", HttpMethod.POST, request, String.class);
        System.out.println(response.getBody());
    }
    
    @Test
    public void testAuthorized() {

        JSONObject parm = new JSONObject();
        parm.put("text", "test");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<String> request = new HttpEntity<>(parm.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange("/comment", HttpMethod.POST, request, String.class);
        System.out.println(response.getBody());
    }
}
