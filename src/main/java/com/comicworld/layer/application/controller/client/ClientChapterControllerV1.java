package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.service.chapter.fetch_chapter_info_by_fake_id_service.FetchChapterInfoByFakeIdService;
import com.comicworld.layer.domain.service.chapter.fetch_chapter_info_service.FetchChapterInfoService;
import com.comicworld.layer.domain.service.chapter.fetch_chapters_of_comic_service.FetchChaptersOfComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientChapterControllerV1 {

    @Autowired
    @Qualifier("fetchChapterInfoServiceImplV1")
    private FetchChapterInfoService fetchChapterInfoService;

    @Autowired
    @Qualifier("fetchChapterInfoByFakeIdServiceImplV1")
    private FetchChapterInfoByFakeIdService fetchChapterInfoByFakeIdService;

    @Autowired
    @Qualifier("fetchChaptersOfComicServiceImplV1")
    private FetchChaptersOfComicService fetchChaptersOfComicService;

    @GetMapping(path = "/v1/comics/{comicId}/chapters/{chapterId}")
    public ResponseEntity<Object> fetchChapterInfo(@PathVariable("chapterId") Long chapterId) {
        return this.fetchChapterInfoService.fetch(chapterId);
    }

    @GetMapping(path = "/v1/comics/{comicFakeId}/chapters/fake/{chapterFakeId}")
    public ResponseEntity<Object> fetchChapterInfoByFakeId(@PathVariable("comicFakeId") String comicFakeId,
                                                           @PathVariable("chapterFakeId") String chapterFakeId) {
        return this.fetchChapterInfoByFakeIdService.fetch(chapterFakeId, comicFakeId);
    }

    @GetMapping(path = "/v1/comics/{comicId}/chapters")
    public ResponseEntity<Object> fetchChaptersOfComic(@PathVariable("comicId") Long comicId) {
        return this.fetchChaptersOfComicService.fetch(comicId);
    }

}
