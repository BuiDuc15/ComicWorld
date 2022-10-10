package com.comicworld.layer.domain.service.translator_group.search;

import org.springframework.http.ResponseEntity;

public interface TranslatorGroupSearchService {

    public ResponseEntity<Object> search(String keyword);

}
