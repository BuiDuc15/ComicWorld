package com.comicworld.layer.domain.service.translator_group_identity.add_translator_group_member;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityInDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;
import org.springframework.http.ResponseEntity;

public interface AddTranslatorGroupMemberService {

    public ResponseEntity<Object> add(Long translatorGroupId,
                                      Long memberId,
                                      TranslatorGroupIdentityInDTO identityInDTO);

}
