package com.comicworld.layer.domain.dto.comment;

import com.comicworld.layer.domain.dto.account.ClientAccountOutDTO;
import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.entity.comment.Comment;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentOutDTO extends BaseOutDTO {

    private String content;

    private Long createdAt;

    private Long lastUpdatedAt;

    private Boolean isEdited;

    private Long upvote;

    private Long dislike;

    private ClientAccountOutDTO account;

    private Set<ReplyOutDTO> replies;

    public CommentOutDTO(Comment comment) {
        this.setId(comment.getId());
        this.setContent(comment.getContent());
        this.setCreatedAt(comment.getCreatedAt());
        this.setLastUpdatedAt(comment.getLastUpdatedAt());
        this.setEdited(comment.getEdited());
        this.setUpvote(comment.getUpvote());
        this.setDislike(comment.getDislike());

        try {
            this.setReplies(comment.getReplies() == null ? null :
                    comment.getReplies().stream()
                            .map(reply -> new ReplyOutDTO(reply))
                            .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("REPLIES LAZY LOAD FROM COMMENT OUT DTO");
        }

        try {
            this.setAccount(comment.getAccount() == null ? null : new ClientAccountOutDTO(comment.getAccount()));
        }
        catch (LazyInitializationException e) {
            System.out.println("ACCOUNT LAZY LOAD FROM COMMENT OUT DTO");
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

    public Set<ReplyOutDTO> getReplies() {
        return replies;
    }

    public void setReplies(Set<ReplyOutDTO> replies) {
        this.replies = replies;
    }
}
