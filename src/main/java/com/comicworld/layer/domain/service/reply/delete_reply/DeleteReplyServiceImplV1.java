package com.comicworld.layer.domain.service.reply.delete_reply;

import com.comicworld.layer.domain.service.reply.crud.ReplyService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("deleteReplyServiceImplV1")
@Transactional
public class DeleteReplyServiceImplV1 implements DeleteReplyService {

    @Autowired
    @Qualifier("replyServiceImplV1")
    private ReplyService replyService;

    @Override
    public ResponseEntity<Object> delete(Long replyId) {
        this.replyService.deleteById(replyId);

        return new ResponseEntity<>(ResponseBodyFactoryV1.noContentResponseBody(), HttpStatus.NO_CONTENT);
    }
}





























