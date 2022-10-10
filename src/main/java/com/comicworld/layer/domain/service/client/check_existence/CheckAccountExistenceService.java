package com.comicworld.layer.domain.service.client.check_existence;

import org.springframework.http.ResponseEntity;

public interface CheckAccountExistenceService {

    public ResponseEntity<Object> check(String username);

}
