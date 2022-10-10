package com.comicworld.layer.domain.service.chapter_storage.fetch_chapter_storage_batch;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FetchChapterStorageBatchService {

    public ResponseEntity<Object> fetch(List<Long> ids);

}
