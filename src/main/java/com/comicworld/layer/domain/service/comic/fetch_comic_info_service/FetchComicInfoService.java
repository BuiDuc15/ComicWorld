package com.comicworld.layer.domain.service.comic.fetch_comic_info_service;

import org.springframework.http.ResponseEntity;

public interface FetchComicInfoService {

    public ResponseEntity<Object> fetch(Long id);

}
