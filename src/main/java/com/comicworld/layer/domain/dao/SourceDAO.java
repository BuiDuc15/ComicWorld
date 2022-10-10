package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.chapter.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceDAO extends JpaRepository<Source, Long> {

    @Query("SELECT s FROM sources s WHERE s.storageType = :storageType")
    public Optional<Source> findByStorageTypeWithAllRelationshipsLoadedLazily(@Param("storageType") String storageType);

}
