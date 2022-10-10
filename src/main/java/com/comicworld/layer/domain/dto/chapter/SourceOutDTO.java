package com.comicworld.layer.domain.dto.chapter;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.entity.chapter.Source;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SourceOutDTO extends BaseOutDTO {

    private String storageType;

    private String name;

    private Set<ChapterStorageOutDTO> chapterStorages;

    public SourceOutDTO(Source source) {
        this.setId(source.getId());
        this.setStorageType(source.getStorageType());
        this.setName(source.getName());

        try {
            if(source.getChapterStorages() != null)
                this.setChapterStorages(source.getChapterStorages().stream()
                        .map(chapterStorage -> new ChapterStorageOutDTO(chapterStorage))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("SOURCE DATA LAZY LOAD FROM SOURCE OUT DTO");
        }
    }

    public Set<ChapterStorageOutDTO> getChapterStorages() {
        return chapterStorages;
    }

    public void setChapterStorages(Set<ChapterStorageOutDTO> chapterStorages) {
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
