package com.comicworld.layer.domain.service.category.search;

import org.springframework.http.ResponseEntity;

public interface CategorySearchService {

    public ResponseEntity<Object> search(String keyword);

}
