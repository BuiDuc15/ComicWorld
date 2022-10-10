package com.comicworld.layer.domain.dto.comment;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.entity.comment.Reply;

public class ReplyInDTO extends BaseInDTO {

    private String content;

    private Long createdAt;

    private Long lastUpdatedAt;

    private Boolean isEdited;

    private Long upvote;

    private Long dislike;

    private Long accountId;

    private CommentInDTO comment;

    public Reply toReply() {
        Reply reply = new Reply();

        reply.setContent(this.getContent());
        reply.setCreatedAt(this.getCreatedAt());
        reply.setLastUpdatedAt(this.getLastUpdatedAt());
        reply.setEdited(this.getEdited());
        reply.setUpvote(this.getUpvote());
        reply.setDislike(this.getDislike());
        reply.setComment(this.getComment() == null ? null : this.getComment().toComment());

        return reply;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Boolean getEdited() {
        return isEdited;
    }

    public void setEdited(Boolean edited) {
        isEdited = edited;
    }

    public Long getUpvote() {
        return upvote;
    }

    public void setUpvote(Long upvote) {
        this.upvote = upvote;
    }

    public Long getDislike() {
        return dislike;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public CommentInDTO getComment() {
        return comment;
    }

    public void setComment(CommentInDTO comment) {
        this.comment = comment;
    }
}
