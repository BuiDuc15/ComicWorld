package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.service.comic.create_comic_service.CreateComicService;
import com.comicworld.layer.domain.service.comic.fetch_comic_info_service.FetchComicInfoService;
import com.comicworld.layer.domain.service.comic.search_comic_service.SearchComicService;
import com.comicworld.layer.domain.service.comic.update_comic_service.UpdateComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class ComicControllerV1 {

    @Autowired
    @Qualifier("createComicServiceImplV1")
    private CreateComicService createComicService;

    @Autowired
    @Qualifier("updateComicServiceImplV1")
    private UpdateComicService updateComicService;

    @Autowired
    @Qualifier("searchComicServiceImplV1")
    private SearchComicService searchComicService;

    @Autowired
    @Qualifier("fetchComicInfoServiceImplV1")
    private FetchComicInfoService fetchComicInfoService;

    @PostMapping(path = "/v1/comics")
    public ResponseEntity<Object> createComic(@RequestBody ComicInDTO comicInDTO) {
        return this.createComicService.create(comicInDTO);
    }

    @PutMapping(path = "/v1/comics")
    public ResponseEntity<Object> updateComic(@RequestBody ComicInDTO comicInDTO) {
        return this.updateComicService.update(comicInDTO);
    }

    @GetMapping(path = "/v1/comics/search")
    public ResponseEntity<Object> search(@RequestParam("keyword") String keyword) {
        return this.searchComicService.search(keyword);
    }

    @GetMapping(path = "/v1/comics/{comicId}")
    public ResponseEntity<Object> fetchInfo(@PathVariable("comicId") Long id) {
        return this.fetchComicInfoService.fetch(id);
    }



}
