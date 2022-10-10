package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.model.MediaModel;
import com.comicworld.layer.domain.service.media.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class MediaControllerV1 {

    @Autowired
    @Qualifier("mediaServiceImplV1")
    private MediaService mediaService;

    @PostMapping(path = "/v1/media")
    public ResponseEntity<Object> processMedia(MediaModel mediaModel) {
        return this.mediaService.process(mediaModel);
    }

}
