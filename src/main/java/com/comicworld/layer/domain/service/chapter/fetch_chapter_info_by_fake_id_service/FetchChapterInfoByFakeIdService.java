package com.comicworld.layer.domain.service.chapter.fetch_chapter_info_by_fake_id_service;

import org.springframework.http.ResponseEntity;

public interface FetchChapterInfoByFakeIdService {

    public ResponseEntity<Object> fetch(String chapterFakeId, String comicFakeId);

}
