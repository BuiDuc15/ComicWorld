package com.comicworld.layer.domain.service.comic.update_love_of_comic;

import org.springframework.http.ResponseEntity;

public interface UpdateLoveOfComicService {

    public ResponseEntity<Object> update(Long id, String action);

}
