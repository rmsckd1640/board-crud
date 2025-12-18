package com.example.board.mapper;

import com.example.board.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TagMapper {
    //새로운 태그 저장
    void save(Tag tag);

    //이미 있는 태그 이름인지 확인
    Tag findByName(String tagName);

    //게시글, 태그 연결 (중간 테이블 post_tag에 저장)
    //변수가 2개 이상이면 mybatis가 누가 누군지 몰라서 이름표 붙여야됨
    void savePostTag(@Param("postId") Long postId, @Param("tagId") Long tagId);
}
