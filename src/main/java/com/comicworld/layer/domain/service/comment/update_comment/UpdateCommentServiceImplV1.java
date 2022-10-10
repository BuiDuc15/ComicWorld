package com.comicworld.layer.domain.service.comment.update_comment;

import com.comicworld.layer.domain.dto.comment.CommentInDTO;
import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.comment.crud.CommentService;
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

@Service("updateCommentServiceImplV1")
@Transactional
public class UpdateCommentServiceImplV1 implements UpdateCommentService {

    @Autowired
    @Qualifier("commentServiceImplV1")
    private CommentService commentService;

    @Override
    public ResponseEntity<Object> update(CommentInDTO commentInDTO) {
        Optional<Comment> rs = this.commentService.findByIdWithAllRelationshipsLoadedLazily(commentInDTO.getId());

        if(rs.isEmpty()) return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comment with ID " + commentInDTO.getId() + " does not exists.")
        ), HttpStatus.UNPROCESSABLE_ENTITY);

        Comment comment = rs.get();

        comment.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        comment.setContent(commentInDTO.getContent());
        comment.setEdited(true);

        comment = this.commentService.saveOrUpdate(comment);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", comment.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }
}
