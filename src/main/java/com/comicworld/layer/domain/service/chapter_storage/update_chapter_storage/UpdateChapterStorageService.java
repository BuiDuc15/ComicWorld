package com.comicworld.layer.domain.service.chapter_storage.update_chapter_storage;

import com.comicworld.layer.domain.dto.chapter.ChapterStorageInDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UpdateChapterStorageService {

    public ResponseEntity<Object> update(List<ChapterStorageInDTO> chapterStoragesInDTO);

}
