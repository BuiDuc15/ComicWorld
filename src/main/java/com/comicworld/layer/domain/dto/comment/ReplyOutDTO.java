package com.comicworld.layer.domain.dto.comment;

import com.comicworld.layer.domain.dto.account.ClientAccountOutDTO;
import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.entity.comment.Reply;
import org.hibernate.LazyInitializationException;

public class ReplyOutDTO extends BaseOutDTO {

    private String content;

    private Long createdAt;

    private Long lastUpdatedAt;

    private Boolean isEdited;

    private Long upvote;

    private Long dislike;

    private ClientAccountOutDTO account;

    public ReplyOutDTO(Reply reply) {
        this.setId(reply.getId());
        this.setContent(reply.getContent());
        this.setCreatedAt(reply.getCreatedAt());
        this.setLastUpdatedAt(reply.getLastUpdatedAt());
        this.setEdited(reply.getEdited());
        this.setUpvote(reply.getUpvote());
        this.setDislike(reply.getDislike());

        try {
            this.setAccount(reply.getAccount() == null ? null : new ClientAccountOutDTO(reply.getAccount()));
        }
        catch (LazyInitializationException e) {
            System.out.println("ACCOUNT LAZY LOAD FROM REPLY OUT DTO");
        }
    }

    public ClientAccountOutDTO getAccount() {
        return account;
    }

    public void setAccount(ClientAccountOutDTO account) {
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
}
