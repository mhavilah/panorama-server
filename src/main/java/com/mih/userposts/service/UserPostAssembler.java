package com.mih.userposts.service;

import com.mih.userposts.model.UserPost;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class UserPostAssembler {

    public List<UserPost> getAll() {
        // ...
        // get users via UserService
        // get posts via PostService
        // join
        return asList(UserPost.of(1l,"Bart Simpson", "bsimpson"));
    }
}
