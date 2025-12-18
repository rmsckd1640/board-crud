package com.example.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    private Long fileId;
    private Long postId;
    private String fileUrl;
    private String fileName;
    private FileType fileType;

    public File(Long postId, String fileUrl, String fileName, FileType fileType){
        this.postId = postId;
        this.fileUrl = fileUrl;
        this.fileName = fileName;
        this.fileType = fileType;
    }
}
