package com.comicworld.layer.domain.service.comment.delete_comment;

import org.springframework.http.ResponseEntity;

public interface DeleteCommentService {

    public ResponseEntity<Object> delete(Long commentId);

}
