package com.comicworld.layer.domain.service.comic.update_view_of_comic;

import org.springframework.http.ResponseEntity;

public interface UpdateViewOfComicService {

    public ResponseEntity<Object> update(Long id);

}
