package com.comicworld.layer.domain.service.comic.fetch_full_comic_info;

import org.springframework.http.ResponseEntity;

public interface FetchFullComicInfoService {

    public ResponseEntity<Object> fetch(Long id);

}
