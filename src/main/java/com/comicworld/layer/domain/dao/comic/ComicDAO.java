package com.comicworld.layer.domain.dao.comic;

import com.comicworld.layer.domain.dao.comic.CustomComicDAO;
import com.comicworld.layer.domain.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComicDAO extends JpaRepository<Comic, Long>, CustomComicDAO {

    @Query("SELECT c FROM comics c LEFT JOIN FETCH c.authors a LEFT JOIN FETCH c.categories cate LEFT JOIN FETCH c.translatorGroups tg LEFT JOIN FETCH c.alternativeNames an LEFT JOIN FETCH a.comics LEFT JOIN FETCH cate.comics LEFT JOIN FETCH tg.comics WHERE c.id = :id")
    public Optional<Comic> findByIdWithChaptersLoadedLazily(@Param("id") Long id);

    @Query("SELECT c FROM comics c WHERE c.id = :id")
    public Optional<Comic> findByIdWithAllRelationshipsLoadedLazily(@Param("id") Long id);

    @Query("SELECT c FROM comics c LEFT JOIN FETCH c.authors LEFT JOIN FETCH c.categories LEFT JOIN FETCH c.chapters LEFT JOIN FETCH c.translatorGroups LEFT JOIN FETCH c.alternativeNames LEFT JOIN FETCH c.comments WHERE c.id = :id")
    public Optional<Comic> findByIdWithAllRelationshipsLoadedEagerly(@Param("id") Long id);

    @Query("SELECT c FROM comics c LEFT JOIN FETCH c.authors LEFT JOIN FETCH c.categories LEFT JOIN FETCH c.chapters LEFT JOIN FETCH c.translatorGroups LEFT JOIN FETCH c.alternativeNames LEFT JOIN FETCH c.comments WHERE c.fakeId = :fakeId")
    public Optional<Comic> findByFakeIdWithAllRelationshipsLoadedEagerly(@Param("fakeId") String fakeId);

    @Query("SELECT c FROM comics c JOIN c.alternativeNames an WHERE an.id IN :ids")
    public List<Comic> findByAlternativeNameIdsWithAllRelationshipsLoadedLazily(@Param("ids") Collection<Long> alternativeNameIds);

    @Query("SELECT c FROM comics c LEFT JOIN FETCH c.chapters JOIN c.alternativeNames an WHERE an.id IN :ids")
    public List<Comic> findByAlternativeNameIdsInWithChaptersLoadedEagerly(@Param("ids") Collection<Long> ids);
}



































