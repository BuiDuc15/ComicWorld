package com.comicworld.layer.domain.service.comic.fetch_popular_comics;

import org.springframework.http.ResponseEntity;

public interface FetchPopularComicsService {

    public ResponseEntity<Object> fetch();

}
