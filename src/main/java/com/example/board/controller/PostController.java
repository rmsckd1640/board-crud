package com.example.board.controller;

import com.example.board.domain.Post;
import com.example.board.dto.PostSaveDto;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/posts")
    //HTTP 응답을 상태 코드, 헤더, 본문을 포함해 개발자가 직접 구성하기 위해 ResponseEntity 사용
    public ResponseEntity<Post> save (@Valid @RequestBody PostSaveDto postSaveDto){ //들어오는 요청값 검사하기 위해 @Valid
        log.info("[Controller] 게시글 저장 요청 들어옴");
        Post requestPost = postService.save(postSaveDto); //postService에서 return 해준 덕분
        log.info("[Controller] 게시글 저장 성공. 게시글 id: {}", requestPost.getPostId());
        return ResponseEntity.status(HttpStatus.CREATED).body(requestPost); //결과로 테이블에 들어간 값 보기
    }

}
