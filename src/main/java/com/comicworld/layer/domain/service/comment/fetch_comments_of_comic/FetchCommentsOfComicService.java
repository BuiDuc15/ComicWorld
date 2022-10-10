package com.comicworld.layer.domain.service.comment.fetch_comments_of_comic;

import org.springframework.http.ResponseEntity;

public interface FetchCommentsOfComicService {

    public ResponseEntity<Object> fetch(Long comicId, Integer page);

}
