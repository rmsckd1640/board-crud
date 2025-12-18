package com.example.board.service;

import com.example.board.domain.Tag;
import com.example.board.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
    private final TagMapper tagMapper;

    //태그생성
    @Transactional
    public void registerTags(Long postId, List<String> tagNames){

        //태그가 없으면 아무것도 안하기
        if(tagNames == null || tagNames.isEmpty()){
            return;
        }

        //중복 태그 제거
        List<String> distinctTags = tagNames.stream() // 1.벨트에 올린다
                .distinct() // 2.중복을 제거한다 (작업자)
                .toList(); // 3.다시 리스트로 포장한다

        for(String tagName: distinctTags){
            checkTagToSave(postId, tagName);
        }
    }

    private void checkTagToSave(Long postId, String tagName){
        //태그 이름 있는지 확인해보자.
        Tag tag = tagMapper.findByName(tagName);

        //없으면 새로 저장
        if(tag == null){
                tag = new Tag(tagName);
                tagMapper.save(tag);
        }

        //게시글, 태그 중간 테이블 매핑
        tagMapper.savePostTag(postId, tag.getTagId());
    }
}
