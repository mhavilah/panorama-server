package com.mih.userposts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This test mocks a REST request to the Users and Posts API via a MockMVC request.
 * The server isn't started during this process.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserPostEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/usersAndPosts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                    .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
        ;
    }
}
