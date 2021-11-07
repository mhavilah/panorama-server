package com.mih.userposts.service;

import com.mih.userposts.model.User;
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
 * Retrieve data from the downstream User service.
 * <p>
 * Based on https://www.baeldung.com/spring-5-webclient
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    // TODO - move to config
    private static final String USERS_SERVICE_BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String USERS_SERVICE_URI = "/users";
    private static final int TIMEOUT = 8000;

    // @Cacheable ?
    @Timed(value = "user.api", longTask = true, description = "User API get All Users", histogram = true, percentiles = {0.90, 0.95, 0.9999})
    public List<User> getAll() {
        List<User> users = null;

        log.info("Retrieving all users - {}{}", USERS_SERVICE_BASE_URL, USERS_SERVICE_URI);

        try {
            users = WebClient.create(USERS_SERVICE_BASE_URL)
                    .get()
                    .uri(USERS_SERVICE_URI)
                    .accept(MediaType.APPLICATION_JSON)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve()
                    .toEntityList(User.class)
                    .block(Duration.ofMillis(TIMEOUT))
                    .getBody();

            log.info("Successfully retrieved: {} users", users.size());
        }
        catch (Exception e) {
            log.error( "Failed to retrieve users from API: {}", ExceptionUtils.getRootCauseMessage(e));
            users = new ArrayList<>();
        }
        return users;
    }

}
