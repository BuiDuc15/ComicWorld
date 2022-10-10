package com.comicworld.layer.domain.service.comment.fetch_comment_info;

import org.springframework.http.ResponseEntity;

public interface FetchCommentInfoService {

    public ResponseEntity<Object> fetch(Long commentId);

}
