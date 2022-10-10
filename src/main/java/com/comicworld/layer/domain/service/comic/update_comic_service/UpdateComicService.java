package com.comicworld.layer.domain.service.comic.update_comic_service;

import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import org.springframework.http.ResponseEntity;

public interface UpdateComicService {

    public ResponseEntity<Object> update(ComicInDTO comicInDTO);

}
