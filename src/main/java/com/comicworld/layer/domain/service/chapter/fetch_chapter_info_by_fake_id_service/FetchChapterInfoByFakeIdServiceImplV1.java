package com.comicworld.layer.domain.service.chapter.fetch_chapter_info_by_fake_id_service;

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

@Service("fetchChapterInfoByFakeIdServiceImplV1")
public class FetchChapterInfoByFakeIdServiceImplV1 implements FetchChapterInfoByFakeIdService {

    @Autowired
    @Qualifier("chapterServiceImplV1")
    private ChapterService chapterService;

    @Override
    public ResponseEntity<Object> fetch(String chapterFakeId, String comicFakeId) {
        Optional<Chapter> rs = this.chapterService.findByFakeIdWithChapterStoragesLoadedEagerly(chapterFakeId, comicFakeId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Chapter with fake ID " + chapterFakeId + " does not exists.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Chapter chapter = rs.get();

        chapter.getChapterStorages().forEach(chapterStorage -> chapterStorage.setChapter(null));

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(new ChapterOutDTO(chapter)), HttpStatus.OK);
    }
}
