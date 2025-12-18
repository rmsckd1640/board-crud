package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.dto.PostSaveDto;
import com.example.board.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor //필수 필드 생성자 자동 생성
public class PostService {
    private final PostMapper postMapper;

    @Transactional
    public Post save(PostSaveDto postSaveDto){
        log.debug("[Service] 게시글 저장 로직 시작. 요청 데이터: {}", postSaveDto);
        Post post = new Post(postSaveDto.getTitle(), postSaveDto.getContent());

        log.debug("[Service] DB 저장 시도. 변환된 객체: {}", post);

        postMapper.save(post); //사용자한테 받은 데이터를 DB로

        log.info("[Service] DB 저장 성공. 저장된 게시글 id: {}", post.getPostId());

        return post; //post를 반환하여 Http 응답 body에 들어간 데이터 확인 가능
    }
}
