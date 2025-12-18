package com.example.board.mapper;

import com.example.board.domain.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    //게시글 작성
    void save(Post post); //void가 아닌 int로 한다면 작업에 성공한 row 개수 반환
}
