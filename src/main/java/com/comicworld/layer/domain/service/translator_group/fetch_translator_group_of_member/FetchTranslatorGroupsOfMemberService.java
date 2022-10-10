package com.comicworld.layer.domain.service.translator_group.fetch_translator_group_of_member;

import org.springframework.http.ResponseEntity;

public interface FetchTranslatorGroupsOfMemberService {

    public ResponseEntity<Object> fetch(Long memberId);

}
