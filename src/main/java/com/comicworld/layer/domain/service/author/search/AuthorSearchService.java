package com.comicworld.layer.domain.service.author.search;

import org.springframework.http.ResponseEntity;

public interface AuthorSearchService {

    public ResponseEntity<Object> search(String keyword);

}
