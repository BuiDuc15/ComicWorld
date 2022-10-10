package com.comicworld.layer.domain.service.chapter.fetch_chapters_of_comic_service;

import org.springframework.http.ResponseEntity;

public interface FetchChaptersOfComicService {

    public ResponseEntity<Object> fetch(Long comicId);

}
