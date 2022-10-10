package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.chapter.ChapterStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ChapterStorageDAO extends JpaRepository<ChapterStorage, Long> {

    @Query("SELECT cs FROM chapter_storages cs WHERE cs.id IN :ids ORDER BY cs.id ASC")
    public List<ChapterStorage> findByIdInWithAllRelationshipsLoadedLazily(@Param("ids") Collection<Long> ids);

}
