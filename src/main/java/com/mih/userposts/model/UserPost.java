package com.mih.userposts.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregate User Post model for REST API
 */
@Data
@Builder
public class UserPost {

    private Long id;
    private String name;
    private String username;
    private List<Post> posts;

    // Builder Pattern https://www.baeldung.com/lombok-builder-singular
    public static UserPost of(Long id, String name, String username) {

        UserPost userPost = UserPost.builder()
                .id(id)
                .name(name)
                .username(username)
                .posts(new ArrayList<>())
                .build();

        return userPost;
    }
}
