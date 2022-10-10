package com.comicworld.layer.domain.service.chapter_storage.delete_chapter_storage;

import com.comicworld.layer.domain.service.chapter_storage.crud.ChapterStorageService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("deleteChapterStorageServiceImplV1")
@Transactional
public class DeleteChapterStorageServiceImplV1 implements DeleteChapterStorageService {

    @Autowired
    @Qualifier("chapterStorageServiceImplV1")
    private ChapterStorageService chapterStorageService;

    @Override
    public ResponseEntity<Object> delete(Long id) {
        this.chapterStorageService.deleteById(id);

        return new ResponseEntity<>(ResponseBodyFactoryV1.noContentResponseBody(), HttpStatus.NO_CONTENT);
    }
}
