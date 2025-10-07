package com.example.leaflog.bc.account.auth.application.dto;

import com.example.leaflog.bc.account.user.domain.User;

import java.util.Map;

public record Auth(
        User user,
        Map<String, Object> attributes
) {
    public static Auth of(User user, Map<String, Object> attributes){
        return new Auth(user, attributes);
    }
}
