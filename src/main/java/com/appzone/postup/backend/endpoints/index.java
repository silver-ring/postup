package com.appzone.postup.backend.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mohamed Morsy
 * 
 * index end point
 * 
 */
@RestController
public class index  implements PostupEndpoint {
    
    @RequestMapping(value = "/")
    public String index() {
        return "service is working";
    }
}
