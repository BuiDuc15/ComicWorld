package com.comicworld.layer.domain.service.chapter.create_chapter_service;

import com.comicworld.layer.domain.dto.chapter.ChapterInDTO;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.chapter.crud.ChapterService;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.utils.Env;
import com.comicworld.utils.StringUtils;
import com.comicworld.utils.TimeUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CreateChapterServiceImplV1 implements CreateChapterService {

    @Autowired
    @Qualifier("chapterServiceImplV1")
    private ChapterService chapterService;

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Override
    public ResponseEntity<Object> create(ChapterInDTO chapterInDTO) {
        Optional<Comic> rs = this.comicService.findByIdWithAllRelationshipsLoadedLazily(chapterInDTO.getComicId());

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comic with ID " + chapterInDTO.getComicId() + " does not exists.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Comic comic = rs.get();

        comic.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());

        comic = this.comicService.saveOrUpdate(comic);

        Chapter chapter = new Chapter();

        chapter.setName(chapterInDTO.getName());
        chapter.setFakeId(StringUtils.purify(chapterInDTO.getName()));
        chapter.setCreatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        chapter.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        chapter.setComic(comic);

        chapter = this.chapterService.saveOrUpdate(chapter);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", chapter.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.CREATED);
    }

}


































