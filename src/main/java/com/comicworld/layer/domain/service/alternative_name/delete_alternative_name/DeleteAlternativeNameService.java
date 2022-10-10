package com.comicworld.layer.domain.service.alternative_name.delete_alternative_name;

import org.springframework.http.ResponseEntity;

public interface DeleteAlternativeNameService {

    public ResponseEntity<Object> delete(Long id);

}
