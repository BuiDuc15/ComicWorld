package com.comicworld.layer.domain.service.comic.search_comic_service;

import org.springframework.http.ResponseEntity;

public interface SearchComicService {

    public ResponseEntity<Object> search(String keyword);

}
