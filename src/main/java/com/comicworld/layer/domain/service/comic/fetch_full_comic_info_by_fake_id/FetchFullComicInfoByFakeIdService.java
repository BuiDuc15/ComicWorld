package com.comicworld.layer.domain.service.comic.fetch_full_comic_info_by_fake_id;

import org.springframework.http.ResponseEntity;

public interface FetchFullComicInfoByFakeIdService {

    public ResponseEntity<Object> fetch(String fakeId);

}
