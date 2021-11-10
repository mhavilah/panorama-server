package com.mih.userposts.service;


import com.mih.userposts.model.Post;
import com.mih.userposts.model.User;
import com.mih.userposts.model.UserPost;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserPostAssemblerTest {

    @MockBean
    UserService mockUserService;

    @MockBean
    PostService mockPostService;

    @Autowired
    UserPostAssembler userPostAssembler;

    @Test
    public void givenNoUsersAndPosts_whenAssemble_thenEmptyListAssembled() {
        when(mockPostService.getAll()).thenReturn(new ArrayList<>());
        when(mockUserService.getAll()).thenReturn(new ArrayList<>());

        List<UserPost> userPosts = userPostAssembler.assembleAll();

        assertThat(userPosts, is(empty()));

        verify(mockPostService, times(1)).getAll();
        verify(mockUserService, times(1)).getAll();
    }

    @Test
    public void givenUsersAndPosts_whenAssemble_thenSomeListAssembled() {

        List<User> users = asList(
                User.builder().id(1L).name("Homer").username("hsimpson").build(),
                User.builder().id(2L).name("Marge").username("msimpson").build(),
                User.builder().id(3L).name("Bart").username("bsimpson").build()
        );
        List<Post> posts = asList(
                Post.builder().id(1L).userId(1L).title("t11").body("b11").build(),
                Post.builder().id(2L).userId(1L).title("t12").body("b12").build(),
                Post.builder().id(3L).userId(2L).title("t21").body("b21").build(),
                Post.builder().id(4L).userId(2L).title("t22").body("b22").build(),
                Post.builder().id(5L).userId(2L).title("t23").body("b23").build()
        );

        when(mockPostService.getAll()).thenReturn(posts);
        when(mockUserService.getAll()).thenReturn(users);

        List<UserPost> userPosts = userPostAssembler.assembleAll();

        assertThat(userPosts.size(), is(equalTo(users.size())));
        assertThat(userPosts.get(0).getName(), is(equalTo("Homer")));
        assertThat(userPosts.get(0).getPosts().size(), is(equalTo(2)));
        assertThat(userPosts.get(1).getPosts().size(), is(equalTo(3)));
        assertThat(userPosts.get(1).getName(), is(equalTo("Marge")));
        assertThat(userPosts.get(2).getPosts().size(), is(equalTo(0)));
        assertThat(userPosts.get(2).getName(), is(equalTo("Bart")));

        verify(mockPostService, times(1)).getAll();
        verify(mockUserService, times(1)).getAll();
    }

    // See also: https://www.baeldung.com/injecting-mocks-in-spring
    // On how to use Spring Profiles and @Bean/@Primary to inject mocks.
    // See also: https://www.baeldung.com/spring-boot-testing
    @TestConfiguration
    @Import({UserPostAssemblerImpl.class})
    static class UserPostAssemblerTestConfiguration {
    }
}
