package com.example.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//사용자가 전달하는 데이터 (request dto)
//게시글 작성
@Getter //불변성 지키기 위해 setter 사용 안함
@ToString
@NoArgsConstructor //요청이 오면 컨버터가 기본 생성자 생성한 뒤 리플렉션으로 필드 값 주입
public class PostSaveDto { //데이터 전달 역할

    //null, 빈칸 막음
    @NotBlank(message = "제목이 비었습니다.")
    @Size(max = 255, message = "제목이 너무 깁니다.")
    private String title;

    @NotBlank(message = "내용이 비었습니다.")
    private String content;


}