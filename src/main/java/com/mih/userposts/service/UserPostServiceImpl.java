package com.mih.userposts.service;

import com.mih.userposts.model.UserPost;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserPostServiceImpl implements UserPostService {

    @Autowired
    private UserPostAssemblerImpl assembler;

    // @Cacheable ?
    @Timed(value = "user.post.api", description = "UserPost API get/merge All Users and Posts", histogram = true, percentiles = {0.90, 0.95, 0.9999})
    public List<UserPost> getAll() {
        log.info("Getting all UserPosts");
        return assembler.assembleAll();
    }

    /**
     * Get a subset of the entries.
     *
     * @param offset    starting index
     * @param limit     number of entries to take
     * @return  List
     */
    public List<UserPost> getPage(Long offset, Long limit) {

        log.info("Getting portion of UserPosts from: index {} to: index {}", offset, limit);

        return assembler.assembleAll()
                .stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }

}
