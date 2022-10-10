package com.comicworld.layer.domain.service.translator_group.fetch_translator_group_info_by_id;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupOutDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fetchTranslatorGroupInfoByIdServiceImplV1")
public class FetchTranslatorGroupInfoByIdServiceImplV1 implements FetchTranslatorGroupInfoByIdService {

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

    @Override
    public ResponseEntity<Object> fetch(Long translatorGroupId) {

        Optional<TranslatorGroup> rs = this.translatorGroupService.findByIdWithAllRelationshipsLoadedLazily(translatorGroupId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Translator group with ID " + translatorGroupId + " is not found.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        TranslatorGroup translatorGroup = rs.get();

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                new TranslatorGroupOutDTO(translatorGroup)
        ), HttpStatus.OK);
    }

}































