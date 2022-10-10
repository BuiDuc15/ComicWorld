package com.comicworld.layer.domain.service.translator_group_identity.fetch_translator_group_identity_by_id;

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

@Service("fetchTranslatorGroupIdentityInfoByIdServiceImplV1")
public class FetchTranslatorGroupIdentityInfoByIdServiceImplV1 implements FetchTranslatorGroupIdentityInfoByIdService {

    @Autowired
    @Qualifier("translatorGroupIdentityServiceImplV1")
    private TranslatorGroupIdentityService translatorGroupIdentityService;

    @Override
    public ResponseEntity<Object> fetch(Long translatorGroupIdentityId) {

        Optional<TranslatorGroupIdentity> rs = this.translatorGroupIdentityService.findByIdWithAllRelationshipsLoadedLazily(translatorGroupIdentityId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Translator group identity with ID " + translatorGroupIdentityId + " is not found.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        TranslatorGroupIdentity translatorGroupIdentity = rs.get();

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                new TranslatorGroupIdentityOutDTO(translatorGroupIdentity)
        ), HttpStatus.OK);
    }

}





























