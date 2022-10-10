package com.comicworld.layer.domain.model.storage_resource;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ResourceModel {

    private List<MultipartFile> files;
    private String path;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
