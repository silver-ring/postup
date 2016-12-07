//package com.appzone.postup.backend;
//
//import com.appzone.postup.backend.model.UserEntity;
//import com.appzone.postup.backend.model.UserRepository;
//import java.util.Base64;
//import java.util.UUID;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
///**
// *
// * @author Mohamed Morsy
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext
//@ActiveProfiles("development")
//public class ApplicationTest extends AbstractTestNGSpringContextTests {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private HttpHeaders  headers;
//    
//    @BeforeMethod
//    public void startUp() {
//        UserEntity userEntity = new UserEntity();
//        String username = UUID.randomUUID().toString();
//        String password = UUID.randomUUID().toString();
//        userEntity.setUsername(username);
//        userEntity.setPassword(password);
//        userEntity.setUserRole(UserEntity.UserRole.ROLE_ADMIN);
//        userRepository.save(userEntity);
//
//        String plainCreds = username + ":" + password;
//        byte[] plainCredsBytes = plainCreds.getBytes();
//        byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
//        String base64Creds = new String(base64CredsBytes);
//
//        headers = new HttpHeaders();
//        headers.add("Authorization", "Basic " + base64Creds);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        
//    }
//
////    @Test
////    public void testApproveComment() {
////        
////        HttpHeaders headers = new HttpHeaders();
////        
////        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
////        map.add("text", "test");
////
////        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
////
////        ResponseEntity<String> response = restTemplate.exchange("/comment", HttpMethod.POST, request, String.class);
////        System.out.println(response.getBody());
////    }
//    @Test
//    public void testAuthorized() {
//
//        
//        
//        JSONObject parm = new JSONObject();
//        parm.put("text", "test");                
//
//        HttpEntity<String> request = new HttpEntity<>(parm.toString(), headers);
//
//        ResponseEntity<String> response = restTemplate.exchange("/comment", HttpMethod.POST, request, String.class);
//        System.out.println(response.getBody());
//    }
//}
