package com.comicworld.layer.domain.service.reply.update_upvote_of_reply;

import org.springframework.http.ResponseEntity;

public interface UpdateUpvoteOfReplyService {

    public ResponseEntity<Object> update(Long replyId, String action);

}
