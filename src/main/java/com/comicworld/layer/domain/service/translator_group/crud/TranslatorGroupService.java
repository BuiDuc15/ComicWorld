package com.comicworld.layer.domain.service.translator_group.crud;

import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TranslatorGroupService {

    public TranslatorGroup saveOrUpdate(TranslatorGroup translatorGroup);

    public Optional<TranslatorGroup> findByIdWithAllRelationshipsLoadedLazily(Long id);

    public List<TranslatorGroup> searchByKeyword(String keyword, Integer limit);

    public void createFullTextIndexByColumn(String column);

    public List<TranslatorGroup> findByMemberId(Long memberId);

    public List<TranslatorGroup> findByIdIn(Collection<Long> ids);

    public List<TranslatorGroup> saveOrUpdateAll(Collection<TranslatorGroup> translatorGroups);

    public List<TranslatorGroup> findByComicId(Long id);

}
