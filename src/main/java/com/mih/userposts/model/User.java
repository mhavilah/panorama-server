package com.mih.userposts.model;

import lombok.Data;

/**
 * Model user for parsing Users API JSON Payload
 */
@Data
public class User {
    private Long id;
    private String name;
    private String username;

    // ignore other properties for now...
    // email, address, phone, website, company
}
