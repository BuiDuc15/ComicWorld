package com.comicworld.layer.domain.service.chapter.fetch_chapter_info_service;

import com.comicworld.layer.domain.dto.chapter.ChapterOutDTO;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.chapter.crud.ChapterService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fetchChapterInfoServiceImplV1")
public class FetchChapterInfoServiceImplV1 implements FetchChapterInfoService {

    @Autowired
    @Qualifier("chapterServiceImplV1")
    private ChapterService chapterService;

    @Override
    public ResponseEntity<Object> fetch(Long chapterId) {
        Optional<Chapter> rs = this.chapterService.findByIdWithChapterStoragesLoadedEagerly(chapterId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Chapter with ID " + chapterId + " does not exists.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Chapter chapter = rs.get();

        chapter.getChapterStorages().forEach(chapterStorage -> chapterStorage.setChapter(null));

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(new ChapterOutDTO(chapter)), HttpStatus.OK);
    }
}





























