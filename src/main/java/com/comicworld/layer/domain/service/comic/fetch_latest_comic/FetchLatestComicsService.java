package com.comicworld.layer.domain.service.comic.fetch_latest_comic;

import org.springframework.http.ResponseEntity;

public interface FetchLatestComicsService {

    public ResponseEntity<Object> fetch();

}
