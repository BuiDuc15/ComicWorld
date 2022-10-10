package com.comicworld.layer.domain.service.translator_group.fetch_translator_group_info_by_id;

import org.springframework.http.ResponseEntity;

public interface FetchTranslatorGroupInfoByIdService {

    public ResponseEntity<Object> fetch(Long translatorGroupId);

}
