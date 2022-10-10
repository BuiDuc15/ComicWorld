package com.comicworld.layer.domain.service.alternative_name.fetch_alternative_names_of_comic;

import org.springframework.http.ResponseEntity;

public interface FetchAlternativeNamesOfComicService {

    public ResponseEntity<Object> fetch(Long comicId);

}
