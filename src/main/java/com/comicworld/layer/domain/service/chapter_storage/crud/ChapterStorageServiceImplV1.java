package com.comicworld.layer.domain.service.chapter_storage.crud;

import com.comicworld.layer.domain.dao.ChapterStorageDAO;
import com.comicworld.layer.domain.entity.chapter.ChapterStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service("chapterStorageServiceImplV1")
@Transactional
public class ChapterStorageServiceImplV1 implements ChapterStorageService {

    @Autowired
    private ChapterStorageDAO dao;

    @Override
    public ChapterStorage saveOrUpdate(ChapterStorage chapterStorage) {
        return this.dao.save(chapterStorage);
    }

    @Override
    public List<ChapterStorage> saveOrUpdateAll(Collection<ChapterStorage> chapterStorages) {
        return this.dao.saveAll(chapterStorages);
    }

    @Override
    public List<ChapterStorage> findByIdInWithAllRelationshipsLoadedLazily(Collection<Long> ids) {
        return this.dao.findByIdInWithAllRelationshipsLoadedLazily(ids);
    }

    @Override
    public void deleteAll(Collection<ChapterStorage> chapterStorages) {
        this.dao.deleteAll(chapterStorages);
    }

    @Override
    public void deleteById(Long id) {
        this.dao.deleteById(id);
    }

}
