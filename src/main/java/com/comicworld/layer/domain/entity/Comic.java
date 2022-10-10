package com.comicworld.layer.domain.entity;

import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.entity.comment.Comment;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "comics")
public class Comic extends Base {

    @Column(name = "displayed_name")
    private String displayedName;

    @Column(name = "fake_id")
    private String fakeId;

    @Column(name = "cover_link")
    private String coverLink;

    @Column(name = "description", columnDefinition = "VARCHAR(3000)")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "view")
    private Long view;

    @Column(name = "love")
    private Long love;

    @Column(name = "dislike")
    private Long dislike;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "last_updated_at")
    private Long lastUpdatedAt;

    @OneToMany(mappedBy = "comic")
    @OrderBy("createdAt DESC")
    private Set<Chapter> chapters = new LinkedHashSet<>();

    @OneToMany(mappedBy = "comic", orphanRemoval = true)
    private Set<AlternativeName> alternativeNames = new LinkedHashSet<>();

    @OneToMany(mappedBy = "comic")
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "comics")
    private Set<Author> authors = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "comics")
    private Set<Category> categories = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "comics")
    private Set<TranslatorGroup> translatorGroups = new LinkedHashSet<>();

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Long getLove() {
        return love;
    }

    public void setLove(Long love) {
        this.love = love;
    }

    public Long getDislike() {
        return dislike;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public Set<TranslatorGroup> getTranslatorGroups() {
        return translatorGroups;
    }

    public void setTranslatorGroups(Set<TranslatorGroup> translatorGroups) {
        this.translatorGroups = translatorGroups;
    }

    public Set<AlternativeName> getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(Set<AlternativeName> alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }

    public String getCoverLink() {
        return coverLink;
    }

    public void setCoverLink(String coverLink) {
        this.coverLink = coverLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
