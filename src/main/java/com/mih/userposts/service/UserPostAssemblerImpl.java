package com.mih.userposts.service;

import com.mih.userposts.model.Post;
import com.mih.userposts.model.User;
import com.mih.userposts.model.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

@Component
public class UserPostAssemblerImpl implements UserPostAssembler {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    public List<UserPost> assembleAll() {
        // join
        List<User> users = userService.getAll();
        List<Post> posts = postService.getAll();

        return users.stream().reduce(new ArrayList<>(),
                (userPosts, user) -> {
                    UserPost userPost = UserPost.of(user);

                    List<Post> filteredPosts = posts.stream()
                            .filter(p -> p.getUserId().equals(user.getId()))
                            .collect(toList());

                    userPost.setPosts(filteredPosts);
                    userPosts.add(userPost);

                    return userPosts;
                }, (l1, l2) -> Stream.of(l1, l2)
                        .flatMap(Collection::stream)
                        .collect(toCollection(ArrayList::new)));

    }
}
