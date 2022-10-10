package com.comicworld.layer.domain.service.comment.delete_comment;

import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.comment.crud.CommentService;
import com.comicworld.layer.domain.service.reply.crud.ReplyService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("deleteCommentServiceImplV1")
@Transactional
public class DeleteCommentServiceImplV1 implements DeleteCommentService {

    @Autowired
    @Qualifier("commentServiceImplV1")
    private CommentService commentService;

    @Autowired
    @Qualifier("replyServiceImplV1")
    private ReplyService replyService;

    @Override
    public ResponseEntity<Object> delete(Long commentId) {
        Optional<Comment> rs = this.commentService.findByIdWithRepliesLoadedEagerly(commentId);

        if(rs.isEmpty()) return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comment with ID " + commentId + " does not exists.")
        ), HttpStatus.UNPROCESSABLE_ENTITY);

        Comment comment = rs.get();

        this.replyService.deleteAll(comment.getReplies());

        this.commentService.deleteById(commentId);

        return new ResponseEntity<>(ResponseBodyFactoryV1.noContentResponseBody(), HttpStatus.NO_CONTENT);
    }
}

































