package com.comicworld.layer.domain.service.media;

import com.comicworld.layer.domain.model.MediaModel;
import org.springframework.http.ResponseEntity;

public interface MediaService {

    public ResponseEntity<Object> process(MediaModel mediaModel);

}
