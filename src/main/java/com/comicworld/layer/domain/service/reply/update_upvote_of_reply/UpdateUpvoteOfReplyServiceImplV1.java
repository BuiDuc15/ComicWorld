package com.comicworld.layer.domain.service.reply.update_upvote_of_reply;

import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.reply.crud.ReplyService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Service("updateUpvoteOfReplyServiceImplV1")
@Transactional
public class UpdateUpvoteOfReplyServiceImplV1 implements UpdateUpvoteOfReplyService {

    @Autowired
    @Qualifier("replyServiceImplV1")
    private ReplyService replyService;

    @Override
    public ResponseEntity<Object> update(Long replyId, String action) {
        try {
            this.replyService.updateUpvoteById(replyId, action);

            HttpHeaders headers = new HttpHeaders();

            headers.set("id", replyId.toString());

            return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
        }
        catch (NoResultException e) {
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, e.getMessage())
            ), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}




































