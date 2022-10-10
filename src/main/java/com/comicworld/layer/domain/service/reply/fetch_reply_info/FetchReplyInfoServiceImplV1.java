package com.comicworld.layer.domain.service.reply.fetch_reply_info;

import com.comicworld.layer.domain.dto.comment.ReplyOutDTO;
import com.comicworld.layer.domain.entity.comment.Reply;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.reply.crud.ReplyService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fetchReplyInfoServiceImplV1")
public class FetchReplyInfoServiceImplV1 implements FetchReplyInfoService {

    @Autowired
    @Qualifier("replyServiceImplV1")
    private ReplyService replyService;

    @Override
    public ResponseEntity<Object> fetch(Long replyId) {
        Optional<Reply> rs = this.replyService.findByIdWithAllRelationshipsLoadedLazily(replyId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Reply with ID " + replyId + " does not exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Reply reply = rs.get();

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(new ReplyOutDTO(reply)), HttpStatus.OK);
    }
}
