package com.comicworld.layer.domain.service.comment.update_comment;

import com.comicworld.layer.domain.dto.comment.CommentInDTO;
import org.springframework.http.ResponseEntity;

public interface UpdateCommentService {

    public ResponseEntity<Object> update(CommentInDTO commentInDTO);

}
