package com.comicworld.layer.domain.service.chapter.crud;

import com.comicworld.layer.domain.dao.ChapterDAO;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("chapterServiceImplV1")
@Transactional
public class ChapterServiceImplV1 implements ChapterService {

    @Autowired
    private ChapterDAO dao;

    @Override
    public Chapter saveOrUpdate(Chapter chapter) {
        return this.dao.save(chapter);
    }

    @Override
    public List<Chapter> saveOrUpdateAll(Collection<Chapter> chapters) {
        return this.dao.saveAll(chapters);
    }

    @Override
    public Optional<Chapter> findByIdWithAllRelationshipsLoadedLazily(Long id) {
        return this.dao.findByIdWithAllRelationshipsLoadedLazily(id);
    }

    @Override
    public void delete(Chapter chapter) {
        this.dao.delete(chapter);
    }

    @Override
    public Optional<Chapter> findByIdWithChapterStoragesLoadedEagerly(Long id) {
        return this.dao.findByIdWithChapterStoragesLoadedEagerly(id);
    }

    @Override
    public Optional<Chapter> findByFakeIdWithChapterStoragesLoadedEagerly(String chapterFakeId, String comicFakeId) {
        return this.dao.findByFakeIdWithChapterStoragesLoadedEagerly(chapterFakeId, comicFakeId);
    }

    @Override
    public List<Chapter> findByComicIdWithAllRelationshipsLoadedLazily(Long id) {
        return this.dao.findByComicIdWithAllRelationshipsLoadedLazily(id);
    }

}
