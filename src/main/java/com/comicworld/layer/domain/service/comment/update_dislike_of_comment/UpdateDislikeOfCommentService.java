package com.comicworld.layer.domain.service.comment.update_dislike_of_comment;

import org.springframework.http.ResponseEntity;

public interface UpdateDislikeOfCommentService {

    public ResponseEntity<Object> update(Long commentId, String action);

}
