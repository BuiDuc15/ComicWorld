package com.comicworld.layer.domain.service.chapter.create_chapter_service;

import com.comicworld.layer.domain.dto.chapter.ChapterInDTO;
import org.springframework.http.ResponseEntity;

public interface CreateChapterService {

    public ResponseEntity<Object> create(ChapterInDTO chapterInDTO);

}
