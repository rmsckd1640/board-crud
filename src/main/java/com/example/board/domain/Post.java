package com.example.board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

//DB와 매핑
@Getter
@ToString //메모리 주소 말고 내용물을 보여줌
@NoArgsConstructor(access = AccessLevel.PROTECTED) //조회시 mybatis가 기본 생성자로 빈 객체 생성하고 컬럼과 필드 매핑함
//protected --> 무분별한 객체 생성 방지 (다른 패키지 생성 x)
public class Post {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //게시글 생성 규칙 강제
    public Post(String title, String content) {
        this.title = title;
        this.content = content;

        //시간 딱 맞추기 위해
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        //DB에서 자동으로 해줘서 필요없지 않나 했지만, 이렇게 해야 응답 반환시 null 말고 값이 찍힘
        //DB의 default는 필요없어졌지만 실수 방지로 냅둠
    }
}
