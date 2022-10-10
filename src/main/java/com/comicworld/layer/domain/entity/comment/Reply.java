package com.comicworld.layer.domain.entity.comment;

import com.comicworld.layer.domain.entity.Base;
import com.comicworld.layer.domain.entity.account.ClientAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "replies")
public class Reply extends Base {

    @Column(name = "content", columnDefinition = "VARCHAR(3000)")
    private String content;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "last_updated_at")
    private Long lastUpdatedAt;

    @Column(name = "is_edited")
    private Boolean isEdited;

    @Column(name = "upvote")
    private Long upvote;

    @Column(name = "dislike")
    private Long dislike;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private ClientAccount account;

    public ClientAccount getAccount() {
        return account;
    }

    public void setAccount(ClientAccount account) {
        this.account = account;
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}








































