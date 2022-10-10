package com.comicworld.layer.domain.service.translator_group_identity.fetch_group_member_info;

import org.springframework.http.ResponseEntity;

public interface FetchGroupMemberInfoService {

    public ResponseEntity<Object> fetch(Long translatorGroupId, Long memberId);

}
