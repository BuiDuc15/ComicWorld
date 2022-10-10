package com.comicworld.layer.domain.service.translator_group_identity.update_translator_group_identity;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityInDTO;
import org.springframework.http.ResponseEntity;

public interface UpdateTranslatorGroupIdentityService {

    public ResponseEntity<Object> update(TranslatorGroupIdentityInDTO translatorGroupIdentityInDTO);

}
