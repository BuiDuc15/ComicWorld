package com.comicworld.layer.domain.service.translator_group.update_translator_group;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupInDTO;
import org.springframework.http.ResponseEntity;

public interface UpdateTranslatorGroupService {

    public ResponseEntity<Object> update(TranslatorGroupInDTO translatorGroupInDTO);

}
