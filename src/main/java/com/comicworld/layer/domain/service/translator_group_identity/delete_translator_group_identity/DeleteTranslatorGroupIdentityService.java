package com.comicworld.layer.domain.service.translator_group_identity.delete_translator_group_identity;

import org.springframework.http.ResponseEntity;

public interface DeleteTranslatorGroupIdentityService {

    public ResponseEntity<Object> delete(Long translatorGroupIdentityId);

}
