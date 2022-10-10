package com.comicworld.layer.domain.dto.chapter;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.entity.chapter.ChapterStorage;

public class ChapterStorageInDTO extends BaseInDTO {

    private String link;

    private Long placeOrder;

    private Long chapterId;

    private String storageType;

    private ChapterInDTO chapter;

    private SourceInDTO source;

    public ChapterStorage toChapterStorage() {
        ChapterStorage chapterStorage = new ChapterStorage();

        chapterStorage.setLink(this.getLink());
        chapterStorage.setPlaceOrder(this.getPlaceOrder());
        chapterStorage.setChapter(this.getChapter() != null ? this.getChapter().toChapter() : null);
        chapterStorage.setSource(this.getSource() != null ? this.getSource().toSource() : null);

        return chapterStorage;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(Long placeOrder) {
        this.placeOrder = placeOrder;
    }

    public ChapterInDTO getChapter() {
        return chapter;
    }

    public void setChapter(ChapterInDTO chapter) {
        this.chapter = chapter;
    }

    public SourceInDTO getSource() {
        return source;
    }

    public void setSource(SourceInDTO source) {
        this.source = source;
    }
}
