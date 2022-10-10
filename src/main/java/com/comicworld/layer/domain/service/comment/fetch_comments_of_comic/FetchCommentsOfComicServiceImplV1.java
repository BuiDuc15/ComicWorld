package com.comicworld.layer.domain.service.comment.fetch_comments_of_comic;

import com.comicworld.layer.domain.dto.comment.CommentOutDTO;
import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.model.PageModel;
import com.comicworld.layer.domain.service.comment.crud.CommentService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fetchCommentsOfComicServiceImplV1")
public class FetchCommentsOfComicServiceImplV1 implements FetchCommentsOfComicService {

    @Autowired
    @Qualifier("commentServiceImplV1")
    private CommentService commentService;

    @Override
    public ResponseEntity<Object> fetch(Long comicId, Integer page) {
        PageModel pageModel = this.commentService.findByComicIdAndPage(comicId, page);

        List<Comment> comments = (List<Comment>) pageModel.getContent();

        List<CommentOutDTO> commentsOutDTO = new ArrayList<>();

        comments.forEach(comment -> commentsOutDTO.add(new CommentOutDTO(comment)));

        pageModel.setContent(commentsOutDTO);

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(pageModel), HttpStatus.OK);
    }
}
