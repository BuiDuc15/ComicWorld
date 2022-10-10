package com.comicworld.layer.domain.service.translator_group_identity.fetch_translator_group_identity_by_id;

import org.springframework.http.ResponseEntity;

public interface FetchTranslatorGroupIdentityInfoByIdService {

    public ResponseEntity<Object> fetch(Long translatorGroupIdentityId);

}
