package com.comicworld.layer.domain.service.chapter.crud;

import com.comicworld.layer.domain.entity.chapter.Chapter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ChapterService {

    public Chapter saveOrUpdate(Chapter chapter);

    public List<Chapter> saveOrUpdateAll(Collection<Chapter> chapters);

    public Optional<Chapter> findByIdWithAllRelationshipsLoadedLazily(Long id);

    public void delete(Chapter chapter);

    public Optional<Chapter> findByIdWithChapterStoragesLoadedEagerly(Long id);

    public Optional<Chapter> findByFakeIdWithChapterStoragesLoadedEagerly(String chapterFakeId, String comicFakeId);

    public List<Chapter> findByComicIdWithAllRelationshipsLoadedLazily(Long id);

}
