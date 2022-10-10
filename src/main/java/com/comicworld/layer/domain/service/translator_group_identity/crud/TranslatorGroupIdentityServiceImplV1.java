package com.comicworld.layer.domain.service.translator_group_identity.crud;

import com.comicworld.layer.domain.dao.TranslatorGroupIdentityDAO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("translatorGroupIdentityServiceImplV1")
@Transactional
public class TranslatorGroupIdentityServiceImplV1 implements TranslatorGroupIdentityService {

    @Autowired
    private TranslatorGroupIdentityDAO dao;

    @Override
    public TranslatorGroupIdentity saveOrUpdate(TranslatorGroupIdentity translatorGroupIdentity) {
        return this.dao.save(translatorGroupIdentity);
    }

    @Override
    public Optional<TranslatorGroupIdentity> findByIdWithAllRelationshipsLoadedLazily(Long id) {
        return this.dao.findByIdWithAllRelationshipsLoadedLazily(id);
    }

    @Override
    public void deleteById(Long translatorGroupIdentityId) {
        this.dao.deleteById(translatorGroupIdentityId);
    }

    @Override
    public Boolean existsById(Long translatorGroupIdentityId) {
        return this.dao.existsById(translatorGroupIdentityId);
    }

    @Override
    public List<TranslatorGroupIdentity> findByTranslatorGroupIdWithAccountLoadedEagerly(Long groupId) {
        return this.dao.findByTranslatorGroupIdWithAccountLoadedEagerly(groupId);
    }

    @Override
    public Optional<TranslatorGroupIdentity> findByTranslatorGroupIdAndAccountIdWithAllRelationshipLoadedLazily(Long translatorGroupId, Long accountId) {
        return this.dao.findByTranslatorGroupIdAndAccountIdWithAllRelationshipLoadedLazily(translatorGroupId, accountId);
    }


}
