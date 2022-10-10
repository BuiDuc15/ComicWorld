package com.comicworld.layer.domain.service.comment.update_upvote_of_comment;

import org.springframework.http.ResponseEntity;

public interface UpdateUpvoteOfCommentService {

    public ResponseEntity<Object> update(Long commentId, String action);

}
