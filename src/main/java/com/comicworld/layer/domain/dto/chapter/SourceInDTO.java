package com.comicworld.layer.domain.dto.chapter;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.entity.chapter.Source;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SourceInDTO extends BaseInDTO {

    private String storageType;

    private String name;

    private Set<ChapterStorageInDTO> chapterStorages;

    public Source toSource() {
        Source source = new Source();

        source.setId(getId());
        source.setStorageType(getStorageType());
        source.setName(getName());

        source.setChapterStorages(this.getChapterStorages() != null ?
                this.getChapterStorages().stream()
                        .map(chapterStorage -> chapterStorage.toChapterStorage())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        return source;
    }

    public Set<ChapterStorageInDTO> getChapterStorages() {
        return chapterStorages;
    }

    public void setChapterStorages(Set<ChapterStorageInDTO> chapterStorages) {
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
