package com.mih.userposts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * End to end API Integration Test.
 *
 * Starts the embedded Tomcat server on a random port and makes a request to the
 * usersAndPosts API.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserPostEndpointIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getAllUsersAndPosts() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/usersAndPosts", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        //        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }
}
