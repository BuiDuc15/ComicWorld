package com.comicworld.layer.domain.model;

public class PageModel {

    private Integer current;

    private Long totalPages;

    private Object content;

    private Long totalElements;

    public PageModel(Integer current, Long totalPages, Object content, Long totalElements) {
        this.current = current;
        this.totalPages = totalPages;
        this.content = content;
        this.totalElements = totalElements;
    }

    public PageModel(Integer current, Long totalPages, Object content) {
        this.current = current;
        this.totalPages = totalPages;
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }
}
