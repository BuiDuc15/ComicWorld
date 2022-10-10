package com.comicworld.layer.domain.service.translator_group_identity.crud;

import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;

import java.util.List;
import java.util.Optional;

public interface TranslatorGroupIdentityService {

    public TranslatorGroupIdentity saveOrUpdate(TranslatorGroupIdentity translatorGroupIdentity);

    public Optional<TranslatorGroupIdentity> findByIdWithAllRelationshipsLoadedLazily(Long id);

    public void deleteById(Long translatorGroupIdentityId);

    public Boolean existsById(Long translatorGroupIdentityId);

    public List<TranslatorGroupIdentity> findByTranslatorGroupIdWithAccountLoadedEagerly(Long groupId);

    public Optional<TranslatorGroupIdentity> findByTranslatorGroupIdAndAccountIdWithAllRelationshipLoadedLazily(Long translatorGroupId, Long accountId);

}
