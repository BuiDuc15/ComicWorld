package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.dto.chapter.ChapterInDTO;
import com.comicworld.layer.domain.service.chapter.create_chapter_service.CreateChapterService;
import com.comicworld.layer.domain.service.chapter.delete_chapter_service.DeleteChapterService;
import com.comicworld.layer.domain.service.chapter.fetch_chapter_info_service.FetchChapterInfoService;
import com.comicworld.layer.domain.service.chapter.fetch_chapters_of_comic_service.FetchChaptersOfComicService;
import com.comicworld.layer.domain.service.chapter.update_chapter_service.UpdateChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class ChapterControllerV1 {

    @Autowired
    @Qualifier("createChapterServiceImplV1")
    private CreateChapterService createChapterService;

    @Autowired
    @Qualifier("updateChapterServiceImplV1")
    private UpdateChapterService updateChapterService;

    @Autowired
    @Qualifier("deleteChapterServiceImplV1")
    private DeleteChapterService deleteChapterService;

    @Autowired
    @Qualifier("fetchChapterInfoServiceImplV1")
    private FetchChapterInfoService fetchChapterInfoService;

    @Autowired
    @Qualifier("fetchChaptersOfComicServiceImplV1")
    private FetchChaptersOfComicService fetchChaptersOfComicService;

    @PostMapping(path = "/v1/comics/{comicId}/chapters")
    public ResponseEntity<Object> createChapter(@RequestBody ChapterInDTO chapterInDTO) {
        return this.createChapterService.create(chapterInDTO);
    }

    @PutMapping(path = "/v1/comics/{comicId}/chapters")
    public ResponseEntity<Object> updateChapter(@RequestBody ChapterInDTO chapterInDTO) {
        return this.updateChapterService.update(chapterInDTO);
    }

    @DeleteMapping(path = "/v1/comics/{comicId}/chapters/{chapterId}")
    public ResponseEntity<Object> deleteChapter(@PathVariable("chapterId") Long chapterId) {
        return this.deleteChapterService.delete(chapterId);
    }

    @GetMapping(path = "/v1/comics/{comicId}/chapters/{chapterId}")
    public ResponseEntity<Object> fetchChapterInfo(@PathVariable("chapterId") Long chapterId) {
        return this.fetchChapterInfoService.fetch(chapterId);
    }

    @GetMapping(path = "/v1/comics/{comicId}/chapters")
    public ResponseEntity<Object> fetchChaptersOfComic(@PathVariable("comicId") Long comicId) {
        return this.fetchChaptersOfComicService.fetch(comicId);
    }

}
