package com.comicworld.layer.domain.service.category.fetch_categories_of_comic;

import org.springframework.http.ResponseEntity;

public interface FetchCategoriesOfComicService {

    public ResponseEntity<Object> fetch(Long comicId);

}
