package com.comicworld.layer.domain.service.translator_group_identity.add_translator_group_member;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityInDTO;
import com.comicworld.layer.domain.entity.account.AdminAccount;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.admin.account.AdminAccountService;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
import com.comicworld.layer.domain.service.translator_group_identity.crud.TranslatorGroupIdentityService;
import com.comicworld.utils.Env;
import com.comicworld.utils.StringUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("addTranslatorGroupMemberServiceImplV1")
@Transactional
public class AddTranslatorGroupMemberServiceImplV1 implements AddTranslatorGroupMemberService {

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

    @Autowired
    @Qualifier("translatorGroupIdentityServiceImplV1")
    private TranslatorGroupIdentityService translatorGroupIdentityService;

    @Autowired
    @Qualifier("adminAccountServiceImplV1")
    private AdminAccountService adminAccountService;

    @Override
    public ResponseEntity<Object> add(Long translatorGroupId, Long memberId, TranslatorGroupIdentityInDTO identityInDTO) {

        Optional<TranslatorGroupIdentity> isMember = this.translatorGroupIdentityService.findByTranslatorGroupIdAndAccountIdWithAllRelationshipLoadedLazily(translatorGroupId, memberId);

        if(isMember.isPresent())
            return new ResponseEntity<>(ResponseBodyFactoryV1.conflictResponseBody(
                    new FailedBodyContentV1(Env.CONFLICT_EXCEPTION_TYPE, "Account with ID " + memberId + " has already been a member of the group.")),
                    HttpStatus.CONFLICT
            );

        Optional<TranslatorGroup> tgFindingRs = this.translatorGroupService.findByIdWithAllRelationshipsLoadedLazily(translatorGroupId);

        if(tgFindingRs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Translator group with ID " + translatorGroupId + " is not found.")),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );

        Optional<AdminAccount> aaFindingRs = this.adminAccountService.findByIdWithAllRelationshipsLoadedLazily(memberId);

        if(aaFindingRs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Account with ID " + memberId + " is not found.")),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );

        TranslatorGroupIdentity translatorGroupIdentity = identityInDTO.toTranslatorGroupIdentity();

        translatorGroupIdentity.setJoinedAt(System.currentTimeMillis());
        translatorGroupIdentity.setFakeId(StringUtils.purify(translatorGroupIdentity.getNickname()));
        translatorGroupIdentity.setTranslatorGroup(tgFindingRs.get());
        translatorGroupIdentity.setAccount(aaFindingRs.get());

        translatorGroupIdentity = this.translatorGroupIdentityService.saveOrUpdate(translatorGroupIdentity);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", translatorGroupIdentity.getId().toString());

        return new ResponseEntity<>(
                ResponseBodyFactoryV1.createdResponseBody(),
                headers,
                HttpStatus.CREATED
        );
    }

}
