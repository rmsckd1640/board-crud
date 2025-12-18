package com.example.board.mapper;

import com.example.board.domain.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    void save(File file);
}
