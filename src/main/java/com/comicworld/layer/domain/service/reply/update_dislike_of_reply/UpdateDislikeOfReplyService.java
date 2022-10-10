package com.comicworld.layer.domain.service.reply.update_dislike_of_reply;

import org.springframework.http.ResponseEntity;

public interface UpdateDislikeOfReplyService {

    public ResponseEntity<Object> update(Long replyId, String action);

}
