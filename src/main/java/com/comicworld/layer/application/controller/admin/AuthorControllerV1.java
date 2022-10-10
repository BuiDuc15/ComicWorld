package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.service.author.fetch_authors_of_comic.FetchAuthorsOfComicService;
import com.comicworld.layer.domain.service.author.search.AuthorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AuthorControllerV1 {

    @Autowired
    @Qualifier("authorSearchServiceImplV1")
    private AuthorSearchService authorSearchService;

    @Autowired
    @Qualifier("fetchAuthorsOfComicServiceImplV1")
    private FetchAuthorsOfComicService fetchAuthorsOfComicService;

    @GetMapping(path = "/v1/authors/search")
    public ResponseEntity<Object> searchByKeyword(@RequestParam("keyword") String keyword) {
        return this.authorSearchService.search(keyword);
    }

    @GetMapping(path = "/v1/comics/{comicId}/authors")
    public ResponseEntity<Object> fetchAuthorsOfComic(@PathVariable("comicId") Long comicId) {
        return this.fetchAuthorsOfComicService.fetch(comicId);
    }

}
