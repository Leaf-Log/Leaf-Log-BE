package com.example.leaflog.bc.til.note.domain.vo;

import com.example.leaflog.bc.til.note.domain.exception.NoteTitleRequiredException;
import com.example.leaflog.bc.til.note.domain.exception.NoteTitleTooLongException;
import jakarta.persistence.Embeddable;

@Embeddable
public record Title(
        String title
) {

    private static final int MAX_TITLE = 20;

    public Title{
        if(title == null || title.isBlank()){
            throw new NoteTitleRequiredException();
        }

        if(title.length() > MAX_TITLE){
            throw new NoteTitleTooLongException();
        }
    }

    public static Title of(String title){
        return new Title(title);
    }
}
