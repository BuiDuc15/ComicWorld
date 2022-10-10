package com.comicworld.layer.domain.service.chapter.fetch_chapter_info_service;

import org.springframework.http.ResponseEntity;

public interface FetchChapterInfoService {

    public ResponseEntity<Object> fetch(Long chapterId);

}
