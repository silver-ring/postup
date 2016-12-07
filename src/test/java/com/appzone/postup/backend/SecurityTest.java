/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appzone.postup.backend;

import com.appzone.postup.backend.model.UserEntity;
import com.appzone.postup.backend.model.UserRepository;
import java.util.Base64;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.MultiValueMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Mohamed Morsy
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("development")
public class SecurityTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUnauthorized() {

        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange("/", HttpMethod.GET, entity, String.class);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);

    }

    @Test
    public void testAuthorized() {

        UserEntity userEntity = new UserEntity();
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setUserRole(UserEntity.UserRole.ROLE_USER);
        userRepository.save(userEntity);

        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange("/", HttpMethod.GET, entity, String.class);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    /** 
     * 
     * @return 
     * 
     * This function return the authorization test parameters as two dimension array
     * [0] user role
     * [1] url to test
     * [3] http method
     * [4] expected http status
     * 
     * @see <a href="http://www.mkyong.com/unittest/testng-parameter-testing-example/">TestNG parameter testing example</a>
     * 
     */        
    @DataProvider(name = "testAuthorization")
    public static Object[][] testAuthorizationParmeters() {
        return new Object[][]{
            {UserEntity.UserRole.ROLE_ADMIN, "/comment", HttpMethod.POST, HttpStatus.OK},
            {UserEntity.UserRole.ROLE_USER, "/comment", HttpMethod.POST, HttpStatus.OK},
            {UserEntity.UserRole.ROLE_ADMIN, "/comment", HttpMethod.GET, HttpStatus.OK},
            {UserEntity.UserRole.ROLE_USER, "/comment", HttpMethod.GET, HttpStatus.FORBIDDEN},
            {UserEntity.UserRole.ROLE_ADMIN, "/comment/approve/1", HttpMethod.PUT, HttpStatus.OK},
            {UserEntity.UserRole.ROLE_USER, "/comment/approve/1", HttpMethod.PUT, HttpStatus.FORBIDDEN},
            {UserEntity.UserRole.ROLE_ADMIN, "/comment/reject/1", HttpMethod.PUT, HttpStatus.OK},
            {UserEntity.UserRole.ROLE_USER, "/comment/reject/1", HttpMethod.PUT, HttpStatus.FORBIDDEN}
        };
    }

    /**
     * 
     * @param userRole
     * @param url
     * @param httpMethod
     * @param expextedHttpStatus 
     * 
     * This test method is for testing the security based on the data provider
     * please note the following:
     *     all requests are json based
     *     username and password gererated as random uuid and saved on the database for authntication resone
     * 
     */
    @Test(dataProvider = "testAuthorization")
    public void testAuthorization(UserEntity.UserRole userRole, String url, HttpMethod httpMethod, HttpStatus expextedHttpStatus) {

        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setUserRole(userRole);
        userRepository.save(userEntity);

        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MultiValueMap> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, entity, String.class);

        Assert.assertEquals(response.getStatusCode(), expextedHttpStatus, userRole.name() + ":" + url + ":" + expextedHttpStatus.name() + ":" + response.getStatusCode().name());

    }

}
