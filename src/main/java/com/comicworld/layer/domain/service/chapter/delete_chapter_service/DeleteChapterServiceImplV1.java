package com.comicworld.layer.domain.service.chapter.delete_chapter_service;

import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.entity.chapter.ChapterStorage;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.chapter.crud.ChapterService;
import com.comicworld.layer.domain.service.chapter_storage.crud.ChapterStorageService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service("deleteChapterServiceImplV1")
@Transactional
public class DeleteChapterServiceImplV1 implements DeleteChapterService {

    @Autowired
    @Qualifier("chapterServiceImplV1")
    private ChapterService chapterService;

    @Autowired
    @Qualifier("chapterStorageServiceImplV1")
    private ChapterStorageService chapterStorageService;

    @Override
    public ResponseEntity<Object> delete(Long chapterId) {

        Optional<Chapter> findingChapterRs = this.chapterService.findByIdWithAllRelationshipsLoadedLazily(chapterId);

        if(findingChapterRs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Chapter with ID " + chapterId + " does not exists.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Chapter chapter = findingChapterRs.get();

        Set<ChapterStorage> chapterStorages = chapter.getChapterStorages();

        this.chapterService.delete(chapter);

        this.chapterStorageService.deleteAll(chapterStorages);

        return new ResponseEntity<>(ResponseBodyFactoryV1.noContentResponseBody(), HttpStatus.NO_CONTENT);
    }
}
