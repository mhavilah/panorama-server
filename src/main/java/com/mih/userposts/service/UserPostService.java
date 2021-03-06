package com.mih.userposts.service;

import com.mih.userposts.model.UserPost;

import java.util.List;

public interface UserPostService {
    public List<UserPost> getAll();

    public List<UserPost> getPage(Long offset, Long limit);
}
