package com.comicworld.layer.domain.service.chapter.update_chapter_service;

import com.comicworld.layer.domain.dto.chapter.ChapterInDTO;
import org.springframework.http.ResponseEntity;

public interface UpdateChapterService {

    public ResponseEntity<Object> update(ChapterInDTO chapterInDTO);

}
