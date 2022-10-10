package com.comicworld.layer.domain.service.admin.check_relationship_admin_account_comic_service;

import org.springframework.http.ResponseEntity;

public interface CheckRelationshipAdminAccountComicService {

    public ResponseEntity<Object> check(Long accountId, Long comicId);

}
