package com.comicworld.layer.domain.service.chapter.fetch_chapters_of_comic_service;

import com.comicworld.layer.domain.dto.chapter.ChapterOutDTO;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.service.chapter.crud.ChapterService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fetchChaptersOfComicServiceImplV1")
public class FetchChaptersOfComicServiceImplV1 implements FetchChaptersOfComicService {

    @Autowired
    @Qualifier("chapterServiceImplV1")
    private ChapterService chapterService;

    @Override
    public ResponseEntity<Object> fetch(Long comicId) {

        List<Chapter> chapters = this.chapterService.findByComicIdWithAllRelationshipsLoadedLazily(comicId);

        List<ChapterOutDTO> chaptersOutDTO = chapters.stream()
                                                    .map(chapter -> new ChapterOutDTO(chapter))
                                                    .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(chaptersOutDTO), HttpStatus.OK);
    }
}
