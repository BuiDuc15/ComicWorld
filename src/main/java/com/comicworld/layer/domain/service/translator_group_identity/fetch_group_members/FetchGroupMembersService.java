package com.comicworld.layer.domain.service.translator_group_identity.fetch_group_members;

import org.springframework.http.ResponseEntity;

public interface FetchGroupMembersService {

    public ResponseEntity<Object> fetch(Long translatorGroupId);

}
