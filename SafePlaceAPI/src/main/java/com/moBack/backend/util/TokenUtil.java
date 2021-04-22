package com.moBack.backend.util;

import net.bytebuddy.utility.RandomString;

import java.util.Date;
import java.util.Optional;

public class TokenUtil {

    private static final String SEPARATOR = "-";

    public static String createToken(String email) {
        return new Date().getTime() + SEPARATOR + email + SEPARATOR + RandomString.make(10);
    }

    public static String getToken(String header) {
        if (header.contains("Bearer")) {
            return header.split(" ")[1];
        }
        return "";
    }

    public static Optional<String> getEmailFromToken(String token) {
        if (token.isEmpty()) return Optional.empty();
        String[] param = token.split(SEPARATOR);
        if (param.length != 3) {
            return Optional.empty();
        }
        return Optional.of(param[1]);
    }
}
