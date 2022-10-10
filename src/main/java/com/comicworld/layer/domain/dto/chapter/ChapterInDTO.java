package com.comicworld.layer.domain.dto.chapter;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.entity.chapter.Chapter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChapterInDTO extends BaseInDTO {

    private String name;

    private Long createdAt;

    private Long lastUpdatedAt;

    private String fakeId;

    private Long comicId;

    private Set<ChapterStorageInDTO> chapterStorages;

    private ComicInDTO comic;

    public Chapter toChapter() {
        Chapter chapter = new Chapter();

        chapter.setName(getName());
        chapter.setCreatedAt(getCreatedAt());
        chapter.setLastUpdatedAt(getLastUpdatedAt());
        chapter.setFakeId(getFakeId());
        chapter.setId(getId());

        chapter.setChapterStorages(this.getChapterStorages() != null ?
                this.getChapterStorages().stream()
                        .map(chapterStorage -> chapterStorage.toChapterStorage())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        chapter.setComic(getComic() != null ? getComic().toComic() : null);

        return chapter;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public Set<ChapterStorageInDTO> getChapterStorages() {
        return chapterStorages;
    }

    public void setChapterStorages(Set<ChapterStorageInDTO> chapterStorages) {
        this.chapterStorages = chapterStorages;
    }

    public ComicInDTO getComic() {
        return comic;
    }

    public void setComic(ComicInDTO comic) {
        this.comic = comic;
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

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }
}
