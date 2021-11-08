package com.mih.userposts.api;

import com.mih.userposts.model.UserPost;
import com.mih.userposts.service.UserPostServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mih.userposts.util.NumberUtils.safeParseLong;

/**
 * The main users and posts API endpoint.
 * This has been configured to support CORS requests from SPA web app clients that
 * originated from another server, eg, NodeJS, running on a different port.
 */
@CrossOrigin
@RestController
public class UserPostEndpoint {

    public static final String USERS_AND_POSTS_ENDPOINT_URI = "/usersAndPosts";
    private final UserPostServiceImpl userPostService;

    private static final Long DEFAULT_PAGE_SIZE = 3L;

    public UserPostEndpoint(UserPostServiceImpl userPostService) {
        this.userPostService = userPostService;
    }



    @GetMapping(USERS_AND_POSTS_ENDPOINT_URI)
    public List<UserPost> all(@RequestParam(required = false) String offset,
                              @RequestParam(required = false) String limit) {

        if (limit != null) {
            return segment(offset, limit);
        }

        return userPostService.getAll();
    }

    private List<UserPost> segment(String offset, String limit) {

        Long offsetIndex = safeParseLong(offset).orElse(0L);
        Long limitIndex = safeParseLong(limit).orElse(DEFAULT_PAGE_SIZE);

        return userPostService.getPage(offsetIndex, limitIndex);
    }
}