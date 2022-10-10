package com.comicworld.layer.domain.dto.comment;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.dto.chapter.ChapterInDTO;
import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.entity.comment.Comment;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentInDTO extends BaseInDTO {

    private String content;

    private Long createdAt;

    private Long lastUpdatedAt;

    private Boolean isEdited;

    private Long upvote;

    private Long dislike;

    private Long accountId;

    private ComicInDTO comic;

    private ChapterInDTO chapter;

    private Set<ReplyInDTO> replies;

    public Comment toComment() {
        Comment comment = new Comment();

        comment.setContent(this.getContent());
        comment.setCreatedAt(this.getCreatedAt());
        comment.setLastUpdatedAt(this.getLastUpdatedAt());
        comment.setEdited(this.getEdited());
        comment.setUpvote(this.getUpvote());
        comment.setDislike(this.getDislike());
        comment.setComic(this.getComic() == null ? null : this.getComic().toComic());
        comment.setChapter(this.getChapter() == null ? null : this.getChapter().toChapter());
        comment.setReplies(this.getReplies() == null ? null :
                            this.getReplies().stream()
                                    .map(reply -> reply.toReply())
                                    .collect(Collectors.toCollection(LinkedHashSet::new)));

        return comment;
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

    public ComicInDTO getComic() {
        return comic;
    }

    public void setComic(ComicInDTO comic) {
        this.comic = comic;
    }

    public ChapterInDTO getChapter() {
        return chapter;
    }

    public void setChapter(ChapterInDTO chapter) {
        this.chapter = chapter;
    }

    public Set<ReplyInDTO> getReplies() {
        return replies;
    }

    public void setReplies(Set<ReplyInDTO> replies) {
        this.replies = replies;
    }
}
