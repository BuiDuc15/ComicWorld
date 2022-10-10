package com.comicworld.layer.domain.service.translator_group.crud;

import com.comicworld.layer.domain.dao.translator_group.TranslatorGroupDAO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("translatorGroupServiceImplV1")
@Transactional
public class TranslatorGroupServiceImplV1 implements TranslatorGroupService {

    @Autowired
    private TranslatorGroupDAO dao;

    @Override
    public TranslatorGroup saveOrUpdate(TranslatorGroup translatorGroup) {
        return this.dao.save(translatorGroup);
    }

    @Override
    public Optional<TranslatorGroup> findByIdWithAllRelationshipsLoadedLazily(Long id) {
        return this.dao.findByIdWithAllRelationshipsLoadedLazily(id);
    }

    @Override
    public List<TranslatorGroup> searchByKeyword(String keyword, Integer limit) {
        return this.dao.searchByKeyword(keyword, limit);
    }

    @Override
    public void createFullTextIndexByColumn(String column) {
        this.dao.createFullTextIndexByColumn(column);
    }

    @Override
    public List<TranslatorGroup> findByMemberId(Long memberId) {
        return this.dao.findByMemberId(memberId);
    }

    @Override
    public List<TranslatorGroup> findByIdIn(Collection<Long> ids) {
        return this.dao.findByIdIn(ids);
    }

    @Override
    public List<TranslatorGroup> saveOrUpdateAll(Collection<TranslatorGroup> translatorGroups) {
        return this.dao.saveAll(translatorGroups);
    }

    @Override
    public List<TranslatorGroup> findByComicId(Long id) {
        return this.dao.findByComicId(id);
    }

}
