package com.example.board.service;

import com.example.board.domain.File;
import com.example.board.domain.FileType;
import com.example.board.mapper.FileMapper;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private final FileMapper fileMapper;
    private final S3Template s3Template;

    //프로젝트 설정에서 설정한 버킷 이름 가져오기 (파일을 담을 최상위 폴더)
    @Value("${spring.cloud.aws.s3.bucket}") //프로젝트 설정에서 설정값 가져옴
    private String bucket;

    @Transactional
    public void registerFiles(Long postId, List<MultipartFile> files){

        //파일 없으면 종료
        if(files == null || files.isEmpty()){
            return;
        }

        for(MultipartFile file : files){
            uploadAndSave(postId, file);
        }
    }

    private void uploadAndSave(Long postId, MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String saveName = UUID.randomUUID() + "_" + originalFilename;

        try(InputStream inputStream = file.getInputStream()){
            ObjectMetadata metadata = ObjectMetadata.builder()
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            var uploadFile = s3Template.upload(bucket, saveName, inputStream, metadata);

            String fileUrl = uploadFile.getURL().toString();
            log.info("S3 업로드 성공: {}", fileUrl);

            //파일타입 정하고 넣기
            FileType fileType = resolveFileType(file);

            File fileEntity = new File(postId, fileUrl, originalFilename, fileType);

            fileMapper.save(fileEntity);

        } catch (IOException e) {
            //추후에 보상 트랜잭션으로 변경 (실패시 파일 삭제. S3에 올라간건 롤백 안됨)
            log.error("파일 업로드 실패: {}", e.getMessage());
            throw new RuntimeException("S3 업로드 중 에러 발생", e);
        }
    }

    private FileType resolveFileType(MultipartFile file) {
        return file.getContentType().startsWith("image")
                ? FileType.IMAGE
                : FileType.ETC;
    }
}
