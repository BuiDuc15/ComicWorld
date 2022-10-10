package com.comicworld.layer.domain.service.client.profile.fetch_profile_of_account;

import org.springframework.http.ResponseEntity;

public interface FetchProfileOfAccountService {

    public ResponseEntity<Object> fetch(Long accountId);

}
