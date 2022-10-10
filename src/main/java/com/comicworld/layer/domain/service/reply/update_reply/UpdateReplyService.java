package com.comicworld.layer.domain.service.reply.update_reply;

import com.comicworld.layer.domain.dto.comment.ReplyInDTO;
import org.springframework.http.ResponseEntity;

public interface UpdateReplyService {

    public ResponseEntity<Object> update(ReplyInDTO replyInDTO);

}
