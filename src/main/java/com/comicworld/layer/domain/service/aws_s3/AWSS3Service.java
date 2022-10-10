package com.comicworld.layer.domain.service.aws_s3;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AWSS3Service {

    public void deleteFile(String path);

    public String uploadFileToPath(MultipartFile multipartFile, String path);

    public List<String> uploadFilesToPath(List<MultipartFile> multipartFiles, String path);

    public String uploadFileToStaticFolder(MultipartFile multipartFile);

}
