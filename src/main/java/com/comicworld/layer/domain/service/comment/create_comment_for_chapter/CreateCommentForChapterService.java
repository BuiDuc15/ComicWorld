package com.comicworld.layer.domain.service.comment.create_comment_for_chapter;

import com.comicworld.layer.domain.dto.comment.CommentInDTO;
import org.springframework.http.ResponseEntity;

public interface CreateCommentForChapterService {

    public ResponseEntity<Object> create(Long chapterId, CommentInDTO commentInDTO);

}
