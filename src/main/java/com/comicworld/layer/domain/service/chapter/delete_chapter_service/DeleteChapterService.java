package com.comicworld.layer.domain.service.chapter.delete_chapter_service;

import org.springframework.http.ResponseEntity;

public interface DeleteChapterService {

    public ResponseEntity<Object> delete(Long chapterId);

}
