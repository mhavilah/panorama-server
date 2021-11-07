package com.mih.userposts.api;

import com.mih.userposts.model.UserPost;
import com.mih.userposts.service.UserPostServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserPostEndpoint {

    private final UserPostServiceImpl userPostService;

    public UserPostEndpoint(UserPostServiceImpl userPostService) {
        this.userPostService = userPostService;
    }

    @GetMapping("/usersAndPosts")
    List<UserPost> all() {

        // TODO - check for pagination query params...
        //  eg, offset=0, limit=3
        //  use getPage()/getHead() instead
        return userPostService.getAll();
    }
}
