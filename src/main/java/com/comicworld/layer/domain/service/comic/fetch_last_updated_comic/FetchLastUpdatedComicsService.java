package com.comicworld.layer.domain.service.comic.fetch_last_updated_comic;

import org.springframework.http.ResponseEntity;

public interface FetchLastUpdatedComicsService {

    public ResponseEntity<Object> fetch();

}
