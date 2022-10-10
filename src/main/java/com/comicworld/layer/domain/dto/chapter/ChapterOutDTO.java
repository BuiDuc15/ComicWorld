package com.comicworld.layer.domain.dto.chapter;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.dto.comment.CommentOutDTO;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChapterOutDTO extends BaseOutDTO {
    private String name;
    private Long createdAt;
    private Long lastUpdatedAt;
    private String fakeId;

    private String comicCoverLink;
    private String comicFakeId;
    private String comicName;
    private Long comicId;
    private Set<ChapterStorageOutDTO> chapterStorages;
    private Set<CommentOutDTO> comments;
    public ChapterOutDTO(Chapter chapter) {
        this.setId(chapter.getId());
        this.setName(chapter.getName());
        this.setCreatedAt(chapter.getCreatedAt());
        this.setLastUpdatedAt(chapter.getLastUpdatedAt());
        this.setFakeId(chapter.getFakeId());

        try {
            if(chapter.getChapterStorages() != null)
                this.setChapterStorages(chapter.getChapterStorages().stream()
                        .map(chapterStorage -> new ChapterStorageOutDTO(chapterStorage))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("SOURCES LAZY LOAD FROM CHAPTER OUT DTO");
        }

        try {
            if(chapter.getComic() != null) {
                this.setComicId(chapter.getComic().getId());
                this.setComicFakeId(chapter.getComic().getFakeId());
                this.setComicName(chapter.getComic().getDisplayedName());
                this.setComicCoverLink(chapter.getComic().getCoverLink());
            }
        }
        catch (LazyInitializationException e) {
            System.out.println("COMIC LAZY LOAD FROM CHAPTER OUT DTO");
        }

        try {
            this.setComments(chapter.getComments() == null ? null :
                            chapter.getComments().stream()
                                    .map(comment -> new CommentOutDTO(comment))
                                    .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("COMIC LAZY LOAD FROM CHAPTER OUT DTO");
        }
    }

    public String getComicCoverLink() {
        return comicCoverLink;
    }

    public void setComicCoverLink(String comicCoverLink) {
        this.comicCoverLink = comicCoverLink;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getComicFakeId() {
        return comicFakeId;
    }

    public void setComicFakeId(String comicFakeId) {
        this.comicFakeId = comicFakeId;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public Set<CommentOutDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentOutDTO> comments) {
        this.comments = comments;
    }

    public Set<ChapterStorageOutDTO> getChapterStorages() {
        return chapterStorages;
    }

    public void setChapterStorages(Set<ChapterStorageOutDTO> chapterStorages) {
        this.chapterStorages = chapterStorages;
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
