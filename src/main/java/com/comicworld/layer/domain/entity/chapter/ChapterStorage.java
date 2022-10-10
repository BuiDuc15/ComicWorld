package com.comicworld.layer.domain.entity.chapter;

import com.comicworld.layer.domain.entity.Base;

import javax.persistence.*;

@Entity(name = "chapter_storages")
public class ChapterStorage extends Base {

    @Column(name = "link")
    private String link;

    @Column(name = "place_order")
    private Long placeOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private Source source;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
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
}
