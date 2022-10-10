package com.comicworld.layer.domain.service.comic.fetch_most_viewed_comics;

import org.springframework.http.ResponseEntity;

public interface FetchMostViewedComicsService {

    public ResponseEntity<Object> fetch();

}
