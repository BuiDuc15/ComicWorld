package com.comicworld.layer.domain.service.translator_group.create_translator_group;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupInDTO;
import org.springframework.http.ResponseEntity;

public interface CreateTranslatorGroupService {

    public ResponseEntity<Object> create(TranslatorGroupInDTO translatorGroupInDTO);

}
