package com.comicworld.layer.domain.service.translator_group.fetch_translator_groups_of_comic;

import org.springframework.http.ResponseEntity;

public interface FetchTranslatorGroupsOfComicService {

    public ResponseEntity<Object> fetch(Long comicId);

}
