package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.chapter.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterDAO extends JpaRepository<Chapter, Long> {

    @Query("SELECT c FROM chapters c WHERE c.id = :id")
    public Optional<Chapter> findByIdWithAllRelationshipsLoadedLazily(@Param("id") Long id);

    @Query("SELECT c FROM chapters c LEFT JOIN FETCH c.chapterStorages cs WHERE c.id = :id")
    public Optional<Chapter> findByIdWithChapterStoragesLoadedEagerly(@Param("id") Long id);

    @Query("SELECT c FROM chapters c LEFT JOIN FETCH c.chapterStorages LEFT JOIN FETCH c.comic c2 WHERE c.fakeId = :chapterFakeId AND c2.fakeId = :comicFakeId")
    public Optional<Chapter> findByFakeIdWithChapterStoragesLoadedEagerly(@Param("chapterFakeId") String chapterFakeId, @Param("comicFakeId") String comicFakeId);

    @Query("SELECT c FROM chapters c JOIN c.comic cc WHERE cc.id = :id ORDER BY c.createdAt DESC")
    public List<Chapter> findByComicIdWithAllRelationshipsLoadedLazily(@Param("id") Long id);

}
