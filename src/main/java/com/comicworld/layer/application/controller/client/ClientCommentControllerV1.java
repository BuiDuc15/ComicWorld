package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.dto.comment.CommentInDTO;
import com.comicworld.layer.domain.service.comment.create_comment_for_chapter.CreateCommentForChapterService;
import com.comicworld.layer.domain.service.comment.create_comment_for_comic.CreateCommentForComicService;
import com.comicworld.layer.domain.service.comment.delete_comment.DeleteCommentService;
import com.comicworld.layer.domain.service.comment.fetch_comment_info.FetchCommentInfoService;
import com.comicworld.layer.domain.service.comment.fetch_comments_of_chapter.FetchCommentsOfChapterService;
import com.comicworld.layer.domain.service.comment.fetch_comments_of_comic.FetchCommentsOfComicService;
import com.comicworld.layer.domain.service.comment.update_comment.UpdateCommentService;
import com.comicworld.layer.domain.service.comment.update_dislike_of_comment.UpdateDislikeOfCommentService;
import com.comicworld.layer.domain.service.comment.update_upvote_of_comment.UpdateUpvoteOfCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientCommentControllerV1 {

    @Autowired
    @Qualifier("fetchCommentsOfChapterServiceImplV1")
    private FetchCommentsOfChapterService fetchCommentsOfChapterService;

    @Autowired
    @Qualifier("fetchCommentsOfComicServiceImplV1")
    private FetchCommentsOfComicService fetchCommentsOfComicService;

    @Autowired
    @Qualifier("updateCommentServiceImplV1")
    private UpdateCommentService updateCommentService;

    @Autowired
    @Qualifier("deleteCommentServiceImplV1")
    private DeleteCommentService deleteCommentService;

    @Autowired
    @Qualifier("createCommentForChapterServiceImplV1")
    private CreateCommentForChapterService createCommentForChapterService;

    @Autowired
    @Qualifier("createCommentForComicServiceImplV1")
    private CreateCommentForComicService createCommentForComicService;

    @Autowired
    @Qualifier("updateUpvoteOfCommentServiceImplV1")
    private UpdateUpvoteOfCommentService updateUpvoteOfCommentService;

    @Autowired
    @Qualifier("updateDislikeOfCommentServiceImplV1")
    private UpdateDislikeOfCommentService updateDislikeOfCommentService;

    @Autowired
    @Qualifier("fetchCommentInfoServiceImplV1")
    private FetchCommentInfoService fetchCommentInfoService;

    @GetMapping(path = "/v1/comments/{commentId}")
    public ResponseEntity<Object> fetchCommentInfo(@PathVariable("commentId") Long commentId) {
        return this.fetchCommentInfoService.fetch(commentId);
    }

    @GetMapping(path = "/v1/comics/{comicId}/chapters/{chapterId}/comments")
    public ResponseEntity<Object> fetchCommentsOfChapter(@PathVariable("chapterId") Long chapterId,
                                                         @RequestParam("page") Integer page) {
        return this.fetchCommentsOfChapterService.fetch(chapterId, page);
    }

    @GetMapping(path = "/v1/comics/{comicId}/comments")
    public ResponseEntity<Object> fetchCommentsOfComic(@PathVariable("comicId") Long comicId,
                                                       @RequestParam("page") Integer page) {
        return this.fetchCommentsOfComicService.fetch(comicId, page);
    }

    @PostMapping(path = "/v1/comics/{comicId}/chapters/{chapterId}/comments")
    public ResponseEntity<Object> createCommentForChapter(@PathVariable("chapterId") Long chapterId,
                                                          @RequestBody CommentInDTO commentInDTO) {
        return this.createCommentForChapterService.create(chapterId, commentInDTO);
    }

    @PostMapping(path = "/v1/comics/{comicId}/comments")
    public ResponseEntity<Object> createCommentForComic(@PathVariable("comicId") Long comicId,
                                                        @RequestBody CommentInDTO commentInDTO) {
        return this.createCommentForComicService.create(comicId, commentInDTO);
    }

    @PutMapping(path = "/v1/comments/{commentId}")
    public ResponseEntity<Object> updateComment(@RequestBody CommentInDTO commentInDTO) {
        return this.updateCommentService.update(commentInDTO);
    }

    @DeleteMapping(path = "/v1/comments/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable("commentId") Long commentId) {
        return this.deleteCommentService.delete(commentId);
    }

    @PutMapping(path = "/v1/comments/{commentId}/upvote")
    public ResponseEntity<Object> updateUpvote(@PathVariable("commentId") Long commentId, String action) {
        return this.updateUpvoteOfCommentService.update(commentId, action);
    }

    @PutMapping(path = "/v1/comments/{commentId}/dislike")
    public ResponseEntity<Object> updateDislike(@PathVariable("commentId") Long commentId, String action) {
        return this.updateDislikeOfCommentService.update(commentId, action);
    }

}




















































