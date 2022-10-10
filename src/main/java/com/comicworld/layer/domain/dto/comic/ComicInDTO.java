package com.comicworld.layer.domain.dto.comic;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameInDTO;
import com.comicworld.layer.domain.dto.author.AuthorInDTO;
import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.dto.category.CategoryInDTO;
import com.comicworld.layer.domain.dto.chapter.ChapterInDTO;
import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupInDTO;
import com.comicworld.layer.domain.entity.Comic;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ComicInDTO extends BaseInDTO {

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

    private Set<ChapterInDTO> chapters;

    private Set<AlternativeNameInDTO> alternativeNames;

    private List<Long> alternativeNameIds;

    private Set<AuthorInDTO> authors;

    private List<Long> authorIds;

    private Set<CategoryInDTO> categories;

    private List<Long> categoryIds;

    private Set<TranslatorGroupInDTO> translatorGroups;

    private List<Long> translatorGroupIds;

    public Comic toComic() {
        Comic comic = new Comic();

        comic.setId(getId());
        comic.setDisplayedName(getDisplayedName());
        comic.setFakeId(getFakeId());
        comic.setCoverLink(getCoverLink());
        comic.setDescription(getDescription());
        comic.setStatus(getStatus());
        comic.setCreatedAt(getCreatedAt());
        comic.setLastUpdatedAt(getLastUpdatedAt());
        comic.setView(getView());
        comic.setLove(getLove());
        comic.setDislike(getDislike());

        comic.setChapters(getChapters() != null ?
                getChapters().stream()
                        .map(chapter -> chapter.toChapter())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        comic.setAlternativeNames(getAlternativeNames() != null ?
                getAlternativeNames().stream()
                        .map(alternativeName -> alternativeName.toAlternativeName())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        comic.setAuthors(getAuthors() != null ?
                getAuthors().stream()
                        .map(author -> author.toAuthor())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        comic.setCategories(getCategories() != null ?
                getCategories().stream()
                        .map(category -> category.toCategory())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        comic.setTranslatorGroups(getTranslatorGroups() != null ?
                getTranslatorGroups().stream()
                        .map(translatorGroup -> translatorGroup.toTranslatorGroup())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        return comic;
    }

    public List<Long> getAlternativeNameIds() {
        return alternativeNameIds;
    }

    public void setAlternativeNameIds(List<Long> alternativeNameIds) {
        this.alternativeNameIds = alternativeNameIds;
    }

    public List<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Long> authorIds) {
        this.authorIds = authorIds;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<Long> getTranslatorGroupIds() {
        return translatorGroupIds;
    }

    public void setTranslatorGroupIds(List<Long> translatorGroupIds) {
        this.translatorGroupIds = translatorGroupIds;
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

    public Set<ChapterInDTO> getChapters() {
        return chapters;
    }

    public void setChapters(Set<ChapterInDTO> chapters) {
        this.chapters = chapters;
    }

    public Set<AlternativeNameInDTO> getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(Set<AlternativeNameInDTO> alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public Set<AuthorInDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorInDTO> authors) {
        this.authors = authors;
    }

    public Set<CategoryInDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryInDTO> categories) {
        this.categories = categories;
    }

    public Set<TranslatorGroupInDTO> getTranslatorGroups() {
        return translatorGroups;
    }

    public void setTranslatorGroups(Set<TranslatorGroupInDTO> translatorGroups) {
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
