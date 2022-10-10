package com.comicworld.layer.domain.service.chapter_storage.create_chapter_storage;

import com.comicworld.layer.domain.dto.chapter.ChapterStorageInDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CreateChapterStorageService {

    public ResponseEntity<Object> create(List<ChapterStorageInDTO> chapterStoragesInDTO);

}
