package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.dto.PostSaveDto;
import com.example.board.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor //필수 필드 생성자 자동 생성
public class PostService {
    private final PostMapper postMapper;
    private final TagService tagService; //태그도 같이 넣기 위해
    private final FileService fileService;

    @Transactional
    public Post save(PostSaveDto postSaveDto, List<MultipartFile> files){ //파일 처리시 dto에서 웹 계층 의존하지말고 여기서 처리
        log.debug("[Service] 게시글 저장 로직 시작. 요청 데이터: {}", postSaveDto);
        Post post = new Post(postSaveDto.getTitle(), postSaveDto.getContent());


        log.debug("[Service] DB 저장 시도. 변환된 객체: {}", post);

        postMapper.save(post); //사용자한테 받은 데이터를 DB로
        tagService.registerTags(post.getPostId(), postSaveDto.getTagNames()); //태그 등록
        fileService.registerFiles(post.getPostId(), files); //파일 등록

        log.info("[Service] DB 저장 성공. 저장된 게시글 id: {}", post.getPostId());

        return post; //post를 반환하여 Http 응답 body에 들어간 데이터 확인 가능
    }
}
