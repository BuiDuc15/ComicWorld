package com.comicworld.layer.domain.service.reply.create_reply_for_comment;

import com.comicworld.layer.domain.dto.comment.ReplyInDTO;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.entity.comment.Reply;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.client.account.ClientAccountService;
import com.comicworld.layer.domain.service.comment.crud.CommentService;
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

@Service("createReplyForCommentServiceImplV1")
@Transactional
public class CreateReplyForCommentServiceImplV1 implements CreateReplyForCommentService {

    @Autowired
    @Qualifier("replyServiceImplV1")
    private ReplyService replyService;

    @Autowired
    @Qualifier("commentServiceImplV1")
    private CommentService commentService;

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    @Override
    public ResponseEntity<Object> create(Long commentId, ReplyInDTO replyInDTO) {
        Optional<Comment> rs = this.commentService.findByIdWithAllRelationshipsLoadedLazily(commentId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comment with ID " + commentId + " does not exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Optional<ClientAccount> rs2 = this.clientAccountService.findByIdWithAllRelationshipsLoadedLazily(replyInDTO.getAccountId());

        if(rs2.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Account with ID " + replyInDTO.getAccountId() + " does not exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        ClientAccount account = rs2.get();

        Comment comment = rs.get();

        Reply reply = replyInDTO.toReply();

        reply.setCreatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        reply.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        reply.setEdited(false);
        reply.setUpvote(0l);
        reply.setDislike(0l);
        reply.setComment(comment);
        reply.setAccount(account);

        reply = this.replyService.saveOrUpdate(reply);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", reply.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }

}










































