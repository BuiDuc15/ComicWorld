package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranslatorGroupIdentityDAO extends JpaRepository<TranslatorGroupIdentity, Long> {

    @Query("SELECT tgi FROM translator_group_identities tgi WHERE tgi.id = :id")
    public Optional<TranslatorGroupIdentity> findByIdWithAllRelationshipsLoadedLazily(@Param("id") Long id);

    @Query("SELECT tgi FROM translator_group_identities tgi LEFT JOIN FETCH tgi.account JOIN tgi.translatorGroup tg WHERE tg.id = :groupId ORDER BY tgi.roleInNumber ASC, tgi.joinedAt ASC")
    public List<TranslatorGroupIdentity> findByTranslatorGroupIdWithAccountLoadedEagerly(@Param("groupId") Long groupId);

    @Query("SELECT tgi FROM translator_group_identities tgi JOIN tgi.translatorGroup tg JOIN tgi.account a WHERE tg.id = :translatorGroupId AND a.id = :accountId")
    public Optional<TranslatorGroupIdentity> findByTranslatorGroupIdAndAccountIdWithAllRelationshipLoadedLazily(@Param("translatorGroupId") Long translatorGroupId,
                                                                                                                @Param("accountId") Long accountId);

}
