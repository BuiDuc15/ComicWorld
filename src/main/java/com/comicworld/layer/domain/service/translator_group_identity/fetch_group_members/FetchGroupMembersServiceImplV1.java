package com.comicworld.layer.domain.service.translator_group_identity.fetch_group_members;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityOutDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;
import com.comicworld.layer.domain.service.translator_group_identity.crud.TranslatorGroupIdentityService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fetchGroupMembersServiceImplV1")
public class FetchGroupMembersServiceImplV1 implements FetchGroupMembersService {

    @Autowired
    @Qualifier("translatorGroupIdentityServiceImplV1")
    private TranslatorGroupIdentityService translatorGroupIdentityService;

    @Override
    public ResponseEntity<Object> fetch(Long translatorGroupId) {
        List<TranslatorGroupIdentity> translatorGroupIdentities = this.translatorGroupIdentityService.findByTranslatorGroupIdWithAccountLoadedEagerly(translatorGroupId);

        List<TranslatorGroupIdentityOutDTO> translatorGroupIdentitiesOutDTO = translatorGroupIdentities.stream()
                .map(translatorGroupIdentity -> {
                    translatorGroupIdentity.getAccount().getProfile().setAccount(null);
                    return new TranslatorGroupIdentityOutDTO(translatorGroupIdentity);
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(translatorGroupIdentitiesOutDTO), HttpStatus.OK);
    }

}
