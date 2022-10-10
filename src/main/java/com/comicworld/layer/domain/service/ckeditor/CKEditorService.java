package com.comicworld.layer.domain.service.ckeditor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CKEditorService {

    public ResponseEntity<Object> upload(MultipartFile multipartFile);

}
