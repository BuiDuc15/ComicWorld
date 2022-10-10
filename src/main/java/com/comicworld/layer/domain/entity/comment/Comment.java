package com.comicworld.layer.domain.entity.comment;

import com.comicworld.layer.domain.entity.Base;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.entity.chapter.Chapter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "comments")
public class Comment extends Base {

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
    @JoinColumn(name = "comic_id")
    private Comic comic;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private ClientAccount account;

    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    @OrderBy("createdAt ASC")
    private Set<Reply> replies = new LinkedHashSet<>();

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

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }
}


































