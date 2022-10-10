package com.comicworld.layer.domain.service.chapter.update_chapter_service;

import com.comicworld.layer.domain.dto.chapter.ChapterInDTO;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.chapter.crud.ChapterService;
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

@Service("updateChapterServiceImplV1")
@Transactional
public class UpdateChapterServiceImplV1 implements UpdateChapterService {

    @Autowired
    @Qualifier("chapterServiceImplV1")
    private ChapterService chapterService;

    @Override
    public ResponseEntity<Object> update(ChapterInDTO chapterInDTO) {

        Optional<Chapter> findingChapterRs = this.chapterService.findByIdWithAllRelationshipsLoadedLazily(chapterInDTO.getId());

        if(findingChapterRs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Chapter with ID " + chapterInDTO.getId() + " does not exists")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Chapter chapter = findingChapterRs.get();

        chapter.setName(chapterInDTO.getName());
        chapter.setFakeId(StringUtils.purify(chapterInDTO.getName()));
        chapter.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());

        chapter = this.chapterService.saveOrUpdate(chapter);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", chapter.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }

}






































