package com.comicworld.layer.domain.service.author.fetch_authors_of_comic;

import org.springframework.http.ResponseEntity;

public interface FetchAuthorsOfComicService {

    public ResponseEntity<Object> fetch(Long comicId);

}
