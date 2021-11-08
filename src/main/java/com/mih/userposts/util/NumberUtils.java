package com.mih.userposts.util;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class NumberUtils {

    public static Optional<Long> safeParseLong(String maybeNumber) {

        try {
            return of(Long.parseLong(maybeNumber));
        } catch (Exception e) {
            return empty();
        }
    }
}
