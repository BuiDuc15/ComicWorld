package com.comicworld.layer.domain.service.admin.account.search;

import org.springframework.http.ResponseEntity;


public interface AdminAccountSearchService {

    public ResponseEntity<Object> search(String keyword);

}
