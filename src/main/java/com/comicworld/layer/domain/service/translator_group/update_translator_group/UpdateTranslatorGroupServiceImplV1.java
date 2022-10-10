package com.comicworld.layer.domain.service.translator_group.update_translator_group;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupInDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
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

@Service("updateTranslatorGroupServiceImplV1")
@Transactional
public class UpdateTranslatorGroupServiceImplV1 implements UpdateTranslatorGroupService {

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

    @Override
    public ResponseEntity<Object> update(TranslatorGroupInDTO translatorGroupInDTO) {

        Optional<TranslatorGroup> rs = this.translatorGroupService.findByIdWithAllRelationshipsLoadedLazily((translatorGroupInDTO.getId()));

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Translator group with ID " + translatorGroupInDTO.getId() + " is not found.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        TranslatorGroup translatorGroup = rs.get();

        translatorGroup.setAvatarLink(translatorGroupInDTO.getAvatarLink());
        translatorGroup.setDescription(translatorGroupInDTO.getDescription());
        translatorGroup.setName(translatorGroupInDTO.getName());
        translatorGroup.setFakeId(StringUtils.purify(translatorGroup.getName()));

        translatorGroup = this.translatorGroupService.saveOrUpdate(translatorGroup);

        HttpHeaders headers = new HttpHeaders();

        headers.add("id", translatorGroup.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }

}
