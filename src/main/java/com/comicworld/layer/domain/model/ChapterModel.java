package com.comicworld.layer.domain.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ChapterModel {

    private List<MultipartFile> files;
    private String storageType;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
}
