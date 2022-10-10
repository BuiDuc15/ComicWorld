package com.comicworld.layer.domain.service.reply.update_reply;

import com.comicworld.layer.domain.dto.comment.ReplyInDTO;
import com.comicworld.layer.domain.entity.comment.Reply;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.reply.crud.ReplyService;
import com.comicworld.utils.Env;
import com.comicworld.utils.TimeUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("updateReplyServiceImplV1")
@Transactional
public class UpdateReplyServiceImplV1 implements UpdateReplyService {

    @Autowired
    @Qualifier("replyServiceImplV1")
    private ReplyService replyService;

    @Override
    public ResponseEntity<Object> update(ReplyInDTO replyInDTO) {
        Optional<Reply> rs = this.replyService.findByIdWithAllRelationshipsLoadedLazily(replyInDTO.getId());

        if(rs.isEmpty()) return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Reply with ID " + replyInDTO.getId() + " does not exists.")
        ), HttpStatus.UNPROCESSABLE_ENTITY);

        Reply reply = rs.get();

        reply.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        reply.setContent(replyInDTO.getContent());
        reply.setEdited(true);

        reply = this.replyService.saveOrUpdate(reply);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", reply.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }
}













































