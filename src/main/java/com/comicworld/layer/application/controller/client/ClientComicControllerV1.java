package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.service.comic.advanced_search_comic_service.AdvancedSearchComicService;
import com.comicworld.layer.domain.service.comic.fetch_comics_of_category.FetchComicsOfCategoryService;
import com.comicworld.layer.domain.service.comic.fetch_full_comic_info.FetchFullComicInfoService;
import com.comicworld.layer.domain.service.comic.fetch_full_comic_info_by_fake_id.FetchFullComicInfoByFakeIdService;
import com.comicworld.layer.domain.service.comic.fetch_last_updated_comic.FetchLastUpdatedComicsService;
import com.comicworld.layer.domain.service.comic.fetch_latest_comic.FetchLatestComicsService;
import com.comicworld.layer.domain.service.comic.fetch_most_viewed_comics.FetchMostViewedComicsService;
import com.comicworld.layer.domain.service.comic.fetch_popular_comics.FetchPopularComicsService;
import com.comicworld.layer.domain.service.comic.search_comic_service.SearchComicService;
import com.comicworld.layer.domain.service.comic.update_dislike_of_comic.UpdateDislikeOfComicService;
import com.comicworld.layer.domain.service.comic.update_love_of_comic.UpdateLoveOfComicService;
import com.comicworld.layer.domain.service.comic.update_view_of_comic.UpdateViewOfComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ClientComicControllerV1 {

    @Autowired
    @Qualifier("advancedSearchComicServiceImplV1")
    private AdvancedSearchComicService advancedSearchComicService;

    @Autowired
    @Qualifier("searchComicServiceImplV1")
    private SearchComicService searchComicService;

    @Autowired
    @Qualifier("fetchFullComicInfoServiceImplV1")
    private FetchFullComicInfoService fetchFullComicInfoService;

    @Autowired
    @Qualifier("fetchComicsOfCategoryServiceImplV1")
    private FetchComicsOfCategoryService fetchComicsOfCategoryService;

    @Autowired
    @Qualifier("updateLoveOfComicServiceImplV1")
    private UpdateLoveOfComicService updateLoveOfComicService;

    @Autowired
    @Qualifier("updateDislikeOfComicServiceImplV1")
    private UpdateDislikeOfComicService updateDislikeOfComicService;

    @Autowired
    @Qualifier("updateViewOfComicServiceImplV1")
    private UpdateViewOfComicService updateViewOfComicService;

    @Autowired
    @Qualifier("fetchMostViewedComicsServiceImplV1")
    private FetchMostViewedComicsService fetchMostViewedComicsService;

    @Autowired
    @Qualifier("fetchPopularComicsServiceImplV1")
    private FetchPopularComicsService fetchPopularComicsService;

    @Autowired
    @Qualifier("fetchLastUpdatedComicsServiceImplV1")
    private FetchLastUpdatedComicsService fetchLastUpdatedComicsService;

    @Autowired
    @Qualifier("fetchLatestComicsServiceImplV1")
    private FetchLatestComicsService fetchLatestComicsService;

    @Autowired
    @Qualifier("fetchFullComicInfoByFakeIdServiceImplV1")
    private FetchFullComicInfoByFakeIdService fetchFullComicInfoByFakeIdService;

    @GetMapping(path = "/v1/comics/advanced-search")
    public ResponseEntity<Object> advancedSearch(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        return this.advancedSearchComicService.search(params);
    }

    @GetMapping(path = "/v1/comics/search")
    public ResponseEntity<Object> search(@RequestParam("keyword") String keyword) {
        return this.searchComicService.search(keyword);
    }

    @GetMapping(path = "/v1/comics/{comicId}")
    public ResponseEntity<Object> fetchFullInfo(@PathVariable("comicId") Long comicId) {
        return this.fetchFullComicInfoService.fetch(comicId);
    }

    @GetMapping(path = "/v1/comics/fake/{fakeId}")
    public ResponseEntity<Object> fetchFullInfoByFakeId(@PathVariable("fakeId") String fakeId) {
        return this.fetchFullComicInfoByFakeIdService.fetch(fakeId);
    }

    @GetMapping(path = "/v1/categories/{fakeId}")
    public ResponseEntity<Object> fetchComicsOfCategory(@PathVariable("fakeId") String fakeId,
                                                        HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        return this.fetchComicsOfCategoryService.fetch(fakeId, params);
    }

    @PutMapping(path = "/v1/comics/{comicId}/love")
    public ResponseEntity<Object> updateLoveOfComic(@PathVariable("comicId") Long comicId,
                                                    String action) {
        return this.updateLoveOfComicService.update(comicId, action);
    }

    @PutMapping(path = "/v1/comics/{comicId}/dislike")
    public ResponseEntity<Object> updateDislikeOfComic(@PathVariable("comicId") Long comicId,
                                                       String action) {
        return this.updateDislikeOfComicService.update(comicId, action);
    }

    @PutMapping(path = "/v1/comics/{comicId}/view")
    public ResponseEntity<Object> updateViewOfComic(@PathVariable("comicId") Long comicId) {
        return this.updateViewOfComicService.update(comicId);
    }

    @GetMapping(path = "/v1/comics/most-viewed")
    public ResponseEntity<Object> fetchMostViewedComics() {
        return this.fetchMostViewedComicsService.fetch();
    }

    @GetMapping(path = "/v1/comics/popular")
    public ResponseEntity<Object> fetchPopularComics() {
        return this.fetchPopularComicsService.fetch();
    }

    @GetMapping(path = "/v1/comics/last-updated")
    public ResponseEntity<Object> fetchLastUpdatedComics() {
        return this.fetchLastUpdatedComicsService.fetch();
    }

    @GetMapping(path = "/v1/comics/latest")
    public ResponseEntity<Object> fetchLatestComics() {
        return this.fetchLatestComicsService.fetch();
    }

}
































