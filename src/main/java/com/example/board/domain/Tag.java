package com.example.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    private Long tagId;
    private String tagName;

    public Tag(String tagName){
        this.tagName = tagName;
    }
}
