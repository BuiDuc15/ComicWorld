package com.comicworld.layer.domain.entity.chapter;

import com.comicworld.layer.domain.entity.Base;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "sources")
public class Source extends Base {

    @Column(name = "storage_type")
    private String storageType;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "source")
    private Set<ChapterStorage> chapterStorages = new LinkedHashSet<>();

    public Set<ChapterStorage> getChapterStorages() {
        return chapterStorages;
    }

    public void setChapterStorages(Set<ChapterStorage> chapterStorages) {
        this.chapterStorages = chapterStorages;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
