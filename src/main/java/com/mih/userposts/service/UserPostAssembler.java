package com.mih.userposts.service;

import com.mih.userposts.model.User;
import com.mih.userposts.model.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPostAssembler {

    @Autowired
    private UserService userService;

//    @Autowired
//    private PostService postService;

    public List<UserPost> getAll() {
        // ...
        // get users via UserService
        // get posts via PostService
        // join
        List<User> users = userService.getAll();

        return users.stream()
                .map( u -> UserPost.of(u.getId(), u.getName(), u.getUsername()))
                .collect(Collectors.toList());

//        return asList(UserPost.of(1l,"Bart Simpson", "bsimpson"));
    }
}
