package com.comicworld.layer.domain.service.comment.fetch_comment_info;

import com.comicworld.layer.domain.dto.comment.CommentOutDTO;
import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.comment.crud.CommentService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fetchCommentInfoServiceImplV1")
public class FetchCommentInfoServiceImplV1 implements FetchCommentInfoService {

    @Autowired
    @Qualifier("commentServiceImplV1")
    private CommentService commentService;

    @Override
    public ResponseEntity<Object> fetch(Long commentId) {
        Optional<Comment> rs = this.commentService.findByIdWithAllRelationshipsLoadedLazily(commentId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comment with ID " + commentId + " does not exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Comment comment = rs.get();

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(new CommentOutDTO(comment)), HttpStatus.OK);
    }
}
