package com.comicworld.layer.domain.service.reply.fetch_reply_info;

import org.springframework.http.ResponseEntity;

public interface FetchReplyInfoService {

    public ResponseEntity<Object> fetch(Long replyId);

}
