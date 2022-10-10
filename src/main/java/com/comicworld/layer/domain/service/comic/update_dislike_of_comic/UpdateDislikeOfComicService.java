package com.comicworld.layer.domain.service.comic.update_dislike_of_comic;

import org.springframework.http.ResponseEntity;

public interface UpdateDislikeOfComicService {

    public ResponseEntity<Object> update(Long id, String action);

}
