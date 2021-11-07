package com.mih.userposts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * A JSON POJO for parsing responses from the downstream Post API.
 * https://projectlombok.org/features/experimental/Jacksonized
 */
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Post {

    private Long userId;
    private Long id;
    private String title;
    private String body;

}
