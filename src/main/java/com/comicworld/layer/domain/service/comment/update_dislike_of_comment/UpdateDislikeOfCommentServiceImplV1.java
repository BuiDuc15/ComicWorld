package com.comicworld.layer.domain.service.comment.update_dislike_of_comment;

import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.comment.crud.CommentService;
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

@Service("updateDislikeOfCommentServiceImplV1")
@Transactional
public class UpdateDislikeOfCommentServiceImplV1 implements UpdateDislikeOfCommentService {

    @Autowired
    @Qualifier("commentServiceImplV1")
    private CommentService commentService;

    @Override
    public ResponseEntity<Object> update(Long commentId, String action) {
        try {
            this.commentService.updateDislikeById(commentId, action);

            HttpHeaders headers = new HttpHeaders();

            headers.set("id", commentId.toString());

            return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
        }
        catch (NoResultException e) {
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, e.getMessage())
            ), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
