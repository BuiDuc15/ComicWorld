package com.comicworld.layer.domain.service.chapter_storage.crud;

import com.comicworld.layer.domain.entity.chapter.ChapterStorage;

import java.util.Collection;
import java.util.List;

public interface ChapterStorageService {

    public ChapterStorage saveOrUpdate(ChapterStorage chapterStorage);

    public List<ChapterStorage> saveOrUpdateAll(Collection<ChapterStorage> chapterStorages);

    public List<ChapterStorage> findByIdInWithAllRelationshipsLoadedLazily(Collection<Long> ids);

    public void deleteAll(Collection<ChapterStorage> chapterStorages);

    public void deleteById(Long id);

}
