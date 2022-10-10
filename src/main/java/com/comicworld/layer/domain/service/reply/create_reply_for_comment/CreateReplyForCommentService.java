package com.comicworld.layer.domain.service.reply.create_reply_for_comment;

import com.comicworld.layer.domain.dto.comment.ReplyInDTO;
import org.springframework.http.ResponseEntity;

public interface CreateReplyForCommentService {

    public ResponseEntity<Object> create(Long commentId, ReplyInDTO replyInDTO);

}
