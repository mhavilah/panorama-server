package com.mih.userposts;

import com.mih.userposts.model.UserPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
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

    // TODO - add Wiremock API server with precanned responses for Users API and Posts API

    @Test
    public void getAllUsersAndPosts() throws Exception {
        ResponseEntity<UserPost[]> response = template.getForEntity("/usersAndPosts", UserPost[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isEqualTo(10);
    }

    @Test
    public void getFirstPageUsersAndPosts() throws Exception {
        ResponseEntity<UserPost[]> response = template.getForEntity("/usersAndPosts?limit=3", UserPost[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isEqualTo(3);
    }

    @Test
    public void getOffsetPageUsersAndPosts() throws Exception {
        ResponseEntity<UserPost[]> response = template.getForEntity("/usersAndPosts?offset=1&limit=1", UserPost[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<UserPost> userPosts = asList(response.getBody());
        assertThat(userPosts.size()).isEqualTo(1);
        assertThat(userPosts.get(0).getId()).isEqualTo(2);
    }

    @Test
    public void getEmptyPageUsersAndPosts() throws Exception {
        ResponseEntity<UserPost[]> response = template.getForEntity("/usersAndPosts?offset=100&limit=1", UserPost[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isEqualTo(0);
    }

    // TODO - test failure, unavailable downstream APIs.
}
