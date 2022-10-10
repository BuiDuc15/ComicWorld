package com.comicworld.layer.domain.service.chapter_storage.upload_chapter;

import com.comicworld.layer.domain.model.ChapterModel;
import org.springframework.http.ResponseEntity;


public interface UploadChapterService {

    public ResponseEntity<Object> upload(ChapterModel chapterModel);

}
