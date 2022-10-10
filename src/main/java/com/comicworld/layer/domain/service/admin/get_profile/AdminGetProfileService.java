package com.comicworld.layer.domain.service.admin.get_profile;

import org.springframework.http.ResponseEntity;

public interface AdminGetProfileService {

    public ResponseEntity<Object> getByAccountId(Long id);

}
