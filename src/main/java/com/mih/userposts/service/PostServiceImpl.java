package com.mih.userposts.service;

import com.mih.userposts.model.Post;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Retrieve data from the downstream Post service.
 * <p>
 * Based on https://www.baeldung.com/spring-5-webclient
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    // TODO - move to config
    private static final String POSTS_SERVICE_BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String POSTS_SERVICE_URI = "/posts";
    private static final int TIMEOUT = 8000;

    // @Cacheable ?
    @Timed(value = "post.api", longTask = true, description = "Post API get All Posts", histogram = true, percentiles = {0.90, 0.95, 0.9999})
    public List<Post> getAll() {
        List<Post> posts = null;

        log.info("Retrieving all posts - {}{}", POSTS_SERVICE_BASE_URL, POSTS_SERVICE_URI);

        try {
            posts = WebClient.create(POSTS_SERVICE_BASE_URL)
                    .get()
                    .uri(POSTS_SERVICE_URI)
                    .accept(MediaType.APPLICATION_JSON)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve()
                    .toEntityList(Post.class)
                    .block(Duration.ofMillis(TIMEOUT))
                    .getBody();

            log.info("Successfully retrieved: {} posts", posts.size());
        }
        catch (Exception e) {
            log.error( "Failed to retrieve posts from API: {}", ExceptionUtils.getRootCauseMessage(e));
            posts = new ArrayList<>();
        }
        return posts;
    }

}
