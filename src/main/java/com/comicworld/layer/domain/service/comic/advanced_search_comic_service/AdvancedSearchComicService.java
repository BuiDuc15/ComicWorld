package com.comicworld.layer.domain.service.comic.advanced_search_comic_service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AdvancedSearchComicService {

    public ResponseEntity<Object> search(Map<String, String[]> params);

}
