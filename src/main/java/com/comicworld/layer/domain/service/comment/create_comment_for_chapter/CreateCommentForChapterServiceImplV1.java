package com.comicworld.layer.domain.service.comment.create_comment_for_chapter;

import com.comicworld.layer.domain.dto.comment.CommentInDTO;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.chapter.crud.ChapterService;
import com.comicworld.layer.domain.service.client.account.ClientAccountService;
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

@Service("createCommentForChapterServiceImplV1")
@Transactional
public class CreateCommentForChapterServiceImplV1 implements CreateCommentForChapterService {

    @Autowired
    @Qualifier("commentServiceImplV1")
    private CommentService commentService;

    @Autowired
    @Qualifier("chapterServiceImplV1")
    private ChapterService chapterService;

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    @Override
    public ResponseEntity<Object> create(Long chapterId, CommentInDTO commentInDTO) {
        Optional<Chapter> rs = this.chapterService.findByIdWithAllRelationshipsLoadedLazily(chapterId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Chapter with ID " + chapterId + " does not exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Optional<ClientAccount> rs2 = this.clientAccountService.findByIdWithAllRelationshipsLoadedLazily(commentInDTO.getAccountId());

        if(rs2.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Account with ID " + commentInDTO.getAccountId() + " does not exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        ClientAccount account = rs2.get();

        Chapter chapter = rs.get();

        Comment comment = commentInDTO.toComment();

        comment.setCreatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        comment.setLastUpdatedAt(TimeUtils.getCurrentUTCTimeInMilliseconds());
        comment.setEdited(false);
        comment.setUpvote(0l);
        comment.setDislike(0l);
        comment.setChapter(chapter);
        comment.setAccount(account);

        comment = this.commentService.saveOrUpdate(comment);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", comment.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }
}
































