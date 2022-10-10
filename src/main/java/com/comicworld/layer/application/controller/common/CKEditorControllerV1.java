package com.comicworld.layer.application.controller.common;

import com.comicworld.layer.domain.service.ckeditor.CKEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CKEditorControllerV1 {

    @Autowired
    @Qualifier("ckEditorServiceImplV1")
    private CKEditorService ckEditorService;

    @PostMapping(path = "/ckeditor/upload")
    public ResponseEntity<Object> handleUpload(@RequestParam(name = "upload") MultipartFile multipartFile) {
        return this.ckEditorService.upload(multipartFile);
    }

}
