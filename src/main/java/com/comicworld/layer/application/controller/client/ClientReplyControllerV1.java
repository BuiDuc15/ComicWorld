package com.comicworld.layer.application.controller.client;

import com.comicworld.layer.domain.dto.comment.ReplyInDTO;
import com.comicworld.layer.domain.service.reply.create_reply_for_comment.CreateReplyForCommentService;
import com.comicworld.layer.domain.service.reply.delete_reply.DeleteReplyService;
import com.comicworld.layer.domain.service.reply.fetch_reply_info.FetchReplyInfoService;
import com.comicworld.layer.domain.service.reply.update_dislike_of_reply.UpdateDislikeOfReplyService;
import com.comicworld.layer.domain.service.reply.update_reply.UpdateReplyService;
import com.comicworld.layer.domain.service.reply.update_upvote_of_reply.UpdateUpvoteOfReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientReplyControllerV1 {

    @Autowired
    @Qualifier("updateReplyServiceImplV1")
    private UpdateReplyService updateReplyService;

    @Autowired
    @Qualifier("deleteReplyServiceImplV1")
    private DeleteReplyService deleteReplyService;

    @Autowired
    @Qualifier("createReplyForCommentServiceImplV1")
    private CreateReplyForCommentService createReplyForCommentService;

    @Autowired
    @Qualifier("updateUpvoteOfReplyServiceImplV1")
    private UpdateUpvoteOfReplyService updateUpvoteOfReplyService;

    @Autowired
    @Qualifier("updateDislikeOfReplyServiceImplV1")
    private UpdateDislikeOfReplyService updateDislikeOfReplyService;

    @Autowired
    @Qualifier("fetchReplyInfoServiceImplV1")
    private FetchReplyInfoService fetchReplyInfoService;

    @GetMapping(path = "/v1/replies/{replyId}")
    public ResponseEntity<Object> fetchReplyInfo(@PathVariable("replyId") Long replyId) {
        return this.fetchReplyInfoService.fetch(replyId);
    }

    @PostMapping(path = "/v1/comments/{commentId}/replies")
    public ResponseEntity<Object> createReplyForComment(@PathVariable("commentId") Long commentId,
                                                        @RequestBody ReplyInDTO replyInDTO) {
        return this.createReplyForCommentService.create(commentId, replyInDTO);
    }

    @PutMapping(path = "/v1/replies/{replyId}")
    public ResponseEntity<Object> updateReply(@RequestBody ReplyInDTO replyInDTO) {
        return this.updateReplyService.update(replyInDTO);
    }

    @DeleteMapping(path = "/v1/replies/{replyId}")
    public ResponseEntity<Object> deleteReply(@PathVariable("replyId") Long replyId) {
        return this.deleteReplyService.delete(replyId);
    }

    @PutMapping(path = "/v1/replies/{replyId}/upvote")
    public ResponseEntity<Object> updateUpvote(@PathVariable("replyId") Long replyId, String action) {
        return this.updateUpvoteOfReplyService.update(replyId, action);
    }

    @PutMapping(path = "/v1/replies/{replyId}/dislike")
    public ResponseEntity<Object> updateDislike(@PathVariable("replyId") Long replyId, String action) {
        return this.updateDislikeOfReplyService.update(replyId, action);
    }

}











































