package com.comicworld.layer.domain.dto.comic;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameOutDTO;
import com.comicworld.layer.domain.dto.author.AuthorOutDTO;
import com.comicworld.layer.domain.dto.authority.AuthorityOutDTO;
import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.dto.category.CategoryOutDTO;
import com.comicworld.layer.domain.dto.chapter.ChapterOutDTO;
import com.comicworld.layer.domain.dto.comment.CommentOutDTO;
import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupOutDTO;
import com.comicworld.layer.domain.entity.Comic;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ComicOutDTO extends BaseOutDTO {

    private String displayedName;

    private String fakeId;

    private String coverLink;

    private String description;

    private Integer status;

    private Long createdAt;

    private Long lastUpdatedAt;

    private Long view;

    private Long love;

    private Long dislike;

    private ChapterOutDTO latestChapter;

    private Set<ChapterOutDTO> chapters;

    private Set<AlternativeNameOutDTO> alternativeNames;

    private Set<AuthorOutDTO> authors;

    private Set<CategoryOutDTO> categories;

    private Set<TranslatorGroupOutDTO> translatorGroups;

    private Set<CommentOutDTO> comments;

    public ComicOutDTO(Comic comic) {
        this.setId(comic.getId());
        this.setDisplayedName(comic.getDisplayedName());
        this.setFakeId(comic.getFakeId());
        this.setCoverLink(comic.getCoverLink());
        this.setDescription(comic.getDescription());
        this.setStatus(comic.getStatus());
        this.setCreatedAt(comic.getCreatedAt());
        this.setLastUpdatedAt(comic.getLastUpdatedAt());
        this.setView(comic.getView());
        this.setLove(comic.getLove());
        this.setDislike(comic.getDislike());

        try {
            if(comic.getChapters() != null)
                this.setChapters(comic.getChapters().stream()
                        .map(chapter -> new ChapterOutDTO(chapter))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("CHAPTERS LAZY LOAD FROM COMIC OUT DTO");
        }

        try {
            if(comic.getAlternativeNames() != null)
                this.setAlternativeNames(comic.getAlternativeNames().stream()
                        .map(alternativeName -> new AlternativeNameOutDTO(alternativeName))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("ALTERNATIVE NAMES LAZY LOAD FROM COMIC OUT DTO");
        }

        try {
            if(comic.getAuthors() != null)
                this.setAuthors(comic.getAuthors().stream()
                        .map(author -> new AuthorOutDTO(author))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("AUTHORS LAZY LOAD FROM COMIC OUT DTO");
        }

        try {
            if(comic.getCategories() != null)
                this.setCategories(comic.getCategories().stream()
                        .map(category -> new CategoryOutDTO(category))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("CATEGORIES LAZY LOAD FROM COMIC OUT DTO");
        }

        try {
            if(comic.getTranslatorGroups() != null)
                this.setTranslatorGroups(comic.getTranslatorGroups().stream()
                        .map(translatorGroup -> new TranslatorGroupOutDTO(translatorGroup))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("TRANSLATOR GROUPS LAZY LOAD FROM COMIC OUT DTO");
        }

        try {
            this.setComments(comic.getComments() == null ? null :
                            comic.getComments().stream()
                                    .map(comment -> new CommentOutDTO(comment))
                                    .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("COMMENTS LAZY LOAD FROM COMIC OUT DTO");
        }
    }

    public Set<CommentOutDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentOutDTO> comments) {
        this.comments = comments;
    }

    public ChapterOutDTO getLatestChapter() {
        return latestChapter;
    }

    public void setLatestChapter(ChapterOutDTO latestChapter) {
        this.latestChapter = latestChapter;
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

    public Set<ChapterOutDTO> getChapters() {
        return chapters;
    }

    public void setChapters(Set<ChapterOutDTO> chapters) {
        this.chapters = chapters;
    }

    public Set<AlternativeNameOutDTO> getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(Set<AlternativeNameOutDTO> alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public Set<AuthorOutDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorOutDTO> authors) {
        this.authors = authors;
    }

    public Set<CategoryOutDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryOutDTO> categories) {
        this.categories = categories;
    }

    public Set<TranslatorGroupOutDTO> getTranslatorGroups() {
        return translatorGroups;
    }

    public void setTranslatorGroups(Set<TranslatorGroupOutDTO> translatorGroups) {
        this.translatorGroups = translatorGroups;
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
