package com.comicworld.layer.domain.entity.chapter;

import com.comicworld.layer.domain.entity.Base;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.comment.Comment;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "chapters")
public class Chapter extends Base {

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "last_updated_at")
    private Long lastUpdatedAt;

    @Column(name = "fake_id")
    private String fakeId;

    @OneToMany(mappedBy = "chapter")
    @OrderBy("placeOrder ASC")
    private Set<ChapterStorage> chapterStorages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "comic", orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_id")
    private Comic comic;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<ChapterStorage> getChapterStorages() {
        return chapterStorages;
    }

    public void setChapterStorages(Set<ChapterStorage> chapterStorages) {
        this.chapterStorages = chapterStorages;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
