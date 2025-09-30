package com.example.leaflog.bc.profile.profile.domain.vo;

import com.example.leaflog.bc.profile.profile.presentation.exception.InvalidDescriptionLengthException;
import jakarta.persistence.Embeddable;

@Embeddable
public record Introduction(
        String introduction
) {
    private static final int MAX_INTRODUCTION = 100;
    public Introduction{
        if(introduction.length() > MAX_INTRODUCTION){
            throw new InvalidDescriptionLengthException();
        }
    }

    public static Introduction of(String value){
        return new Introduction(value);
    }
}
