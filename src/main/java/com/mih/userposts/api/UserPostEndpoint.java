package com.mih.userposts.api;

import com.mih.userposts.model.UserPost;
import com.mih.userposts.service.UserPostServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class UserPostEndpoint {

    private final UserPostServiceImpl userPostService;

    public static final String USERS_AND_POSTS_ENDPOINT_URI = "/usersAndPosts";

    public UserPostEndpoint(UserPostServiceImpl userPostService) {
        this.userPostService = userPostService;
    }

    @GetMapping(USERS_AND_POSTS_ENDPOINT_URI)
    List<UserPost> all() {

        // TODO - check for pagination query params...
        //  eg, offset=0, limit=3
        //  use getPage()/getHead() instead
        return userPostService.getAll();
    }
}
