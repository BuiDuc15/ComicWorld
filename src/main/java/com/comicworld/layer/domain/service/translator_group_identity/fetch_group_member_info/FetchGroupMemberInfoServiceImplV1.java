package com.comicworld.layer.domain.service.translator_group_identity.fetch_group_member_info;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityOutDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.translator_group_identity.crud.TranslatorGroupIdentityService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fetchGroupMemberInfoServiceImplV1")
public class FetchGroupMemberInfoServiceImplV1 implements FetchGroupMemberInfoService {

    @Autowired
    @Qualifier("translatorGroupIdentityServiceImplV1")
    private TranslatorGroupIdentityService translatorGroupIdentityService;

    @Override
    public ResponseEntity<Object> fetch(Long translatorGroupId, Long memberId) {

        Optional<TranslatorGroupIdentity> rs = this.translatorGroupIdentityService.findByTranslatorGroupIdAndAccountIdWithAllRelationshipLoadedLazily(translatorGroupId, memberId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Group with ID " + translatorGroupId + " does not contain the member with ID " + memberId)
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                new TranslatorGroupIdentityOutDTO(rs.get())
        ), HttpStatus.OK);
    }

}
