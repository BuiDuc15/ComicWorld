package com.comicworld.layer.domain.service.comment.fetch_comments_of_chapter;

import org.springframework.http.ResponseEntity;

public interface FetchCommentsOfChapterService {

    public ResponseEntity<Object> fetch(Long chapterId, Integer page);

}
