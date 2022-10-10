package com.comicworld.layer.domain.service.comment.create_comment_for_comic;

import com.comicworld.layer.domain.dto.comment.CommentInDTO;
import org.springframework.http.ResponseEntity;

public interface CreateCommentForComicService {

    public ResponseEntity<Object> create(Long comicId, CommentInDTO commentInDTO);

}
