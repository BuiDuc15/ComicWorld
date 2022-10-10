package com.comicworld.layer.domain.service.reply.delete_reply;

import org.springframework.http.ResponseEntity;

public interface DeleteReplyService {

    public ResponseEntity<Object> delete(Long replyId);

}
