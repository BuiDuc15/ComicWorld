package com.comicworld.layer.domain.model;

public class CKEditorResponseModel {

    private String url;
    private Boolean uploaded;
    private String fileName;

    public CKEditorResponseModel(String url, Boolean uploaded, String fileName) {
        this.url = url;
        this.uploaded = uploaded;
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getUploaded() {
        return uploaded;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
