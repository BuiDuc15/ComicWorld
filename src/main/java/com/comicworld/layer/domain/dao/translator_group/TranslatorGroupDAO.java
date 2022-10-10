package com.comicworld.layer.domain.dao.translator_group;

import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TranslatorGroupDAO extends JpaRepository<TranslatorGroup, Long>, CustomTranslatorGroupDAO {

    @Query("SELECT tg FROM translator_groups tg WHERE tg.id = :id")
    public Optional<TranslatorGroup> findByIdWithAllRelationshipsLoadedLazily(@Param("id") Long id);

    @Query(value = "SELECT * FROM translator_groups tg WHERE MATCH(tg.name) AGAINST (?1 IN BOOLEAN MODE) LIMIT ?2",
            nativeQuery = true)
    public List<TranslatorGroup> searchByKeyword(String keyword, Integer limit);

    @Query("SELECT tg FROM translator_groups tg JOIN tg.translatorGroupIdentities tgi JOIN tgi.account a WHERE a.id = :memberId ORDER BY tg.id ASC")
    public List<TranslatorGroup> findByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT tg FROM translator_groups tg LEFT JOIN FETCH tg.comics WHERE tg.id IN :ids")
    public List<TranslatorGroup> findByIdIn(@Param("ids") Collection<Long> ids);

    @Query("SELECT tg FROM translator_groups tg JOIN tg.comics c WHERE c.id = :id")
    public List<TranslatorGroup> findByComicId(@Param("id") Long id);

}
