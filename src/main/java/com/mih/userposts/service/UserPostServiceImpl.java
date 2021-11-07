package com.mih.userposts.service;

import com.mih.userposts.model.UserPost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserPostServiceImpl implements UserPostService {

    @Autowired
    private UserPostAssemblerImpl assembler;

    // @Cacheable ?
    public List<UserPost> getAll() {
        log.info("Getting all UserPosts");
        return assembler.assembleAll();
    }

//    public Collection<UserPost> getPage(int offset, int limit) {
//
//        Collection<UserPost> userPosts = assembler.getAll().stream().take(size);
//    }

}
