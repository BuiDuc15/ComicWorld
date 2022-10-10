package com.comicworld.layer.domain.service.chapter_storage.delete_chapter_storage;

import org.springframework.http.ResponseEntity;

public interface DeleteChapterStorageService {

    public ResponseEntity<Object> delete(Long id);

}
