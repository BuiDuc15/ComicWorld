package com.comicworld.layer.domain.service.comic.fetch_comics_of_category;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface FetchComicsOfCategoryService {

    public ResponseEntity<Object> fetch(String fakeId, Map<String, String[]> params);

}
