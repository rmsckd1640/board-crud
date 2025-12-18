package com.example.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    //tag는 여러개가 들어올 수 있으므로 List
    //리스트 내부의 각 문자열 길이 검증은 서비스에서 하거나 커스텀 애노테이션 사용
    @Size(max = 10, message = "태그는 최대 10개까지만 등록 가능합니다.")
    private List<String> tagNames;

    //파일 타입과 이름, 크기 등은 MultipartFile에서 받아옴
    //private List<MultipartFile> files;
    //dto 웹 계층 의존 제거
}