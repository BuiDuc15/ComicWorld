package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.service.category.fetch_categories_of_comic.FetchCategoriesOfComicService;
import com.comicworld.layer.domain.service.category.search.CategorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class CategoryControllerV1 {

    @Autowired
    @Qualifier("categorySearchServiceImplV1")
    private CategorySearchService categorySearchService;

    @Autowired
    @Qualifier("fetchCategoriesOfComicServiceImplV1")
    private FetchCategoriesOfComicService fetchCategoriesOfComicService;

    @GetMapping(path = "/v1/categories/search")
    public ResponseEntity<Object> searchByKeyword(@RequestParam("keyword") String keyword) {
        return this.categorySearchService.search(keyword);
    }

    @GetMapping(path = "/v1/comics/{comicId}/categories")
    public ResponseEntity<Object> fetchCategoriesOfComic(@PathVariable("comicId") Long comicId) {
        return this.fetchCategoriesOfComicService.fetch(comicId);
    }

}
