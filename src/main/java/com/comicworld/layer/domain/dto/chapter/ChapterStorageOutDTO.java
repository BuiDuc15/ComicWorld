package com.comicworld.layer.domain.dto.chapter;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.entity.chapter.ChapterStorage;
import org.hibernate.LazyInitializationException;

public class ChapterStorageOutDTO extends BaseOutDTO {

    private String link;

    private Long placeOrder;

    private ChapterOutDTO chapter;

    private SourceOutDTO source;

    public ChapterStorageOutDTO(ChapterStorage chapterStorage) {
        this.setId(chapterStorage.getId());
        this.setLink(chapterStorage.getLink());
        this.setPlaceOrder(chapterStorage.getPlaceOrder());

        try {
            if(chapterStorage.getChapter() != null) {
                this.setChapter(new ChapterOutDTO(chapterStorage.getChapter()));
            }
        }
        catch (LazyInitializationException e) {
            System.out.println("CHAPTER LAZY LOAD FROM CHAPTER OUT DTO");
        }

        try {
            if(chapterStorage.getSource() != null) {
                this.setSource(new SourceOutDTO(chapterStorage.getSource()));
            }
        }
        catch (LazyInitializationException e) {
            System.out.println("SOURCE LAZY LOAD FROM CHAPTER OUT DTO");
        }
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

    public ChapterOutDTO getChapter() {
        return chapter;
    }

    public void setChapter(ChapterOutDTO chapter) {
        this.chapter = chapter;
    }

    public SourceOutDTO getSource() {
        return source;
    }

    public void setSource(SourceOutDTO source) {
        this.source = source;
    }
}
