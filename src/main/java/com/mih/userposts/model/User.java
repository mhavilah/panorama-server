package com.mih.userposts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * A JSON POJO for parsing responses from the downstream User API.
 * https://projectlombok.org/features/experimental/Jacksonized
 */
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class User {
    private Long id;
    private String name;
    private String username;

    // ignore other properties for now...
    // email, address, phone, website, company
}
