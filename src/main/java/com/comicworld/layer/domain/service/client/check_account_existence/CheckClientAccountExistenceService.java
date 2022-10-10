package com.comicworld.layer.domain.service.client.check_account_existence;

import org.springframework.http.ResponseEntity;

public interface CheckClientAccountExistenceService {

    public ResponseEntity<Object> check(String username);

}
