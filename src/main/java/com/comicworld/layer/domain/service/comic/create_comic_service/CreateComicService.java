package com.comicworld.layer.domain.service.comic.create_comic_service;

import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import org.springframework.http.ResponseEntity;

public interface CreateComicService {

    public ResponseEntity<Object> create(ComicInDTO comicInDTO);

}
