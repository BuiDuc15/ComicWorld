package com.comicworld.layer.domain.service.translator_group_identity.update_translator_group_identity;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityInDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
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

@Service("updateTranslatorGroupIdentityServiceImplV1")
@Transactional
public class UpdateTranslatorGroupIdentityServiceImplV1 implements UpdateTranslatorGroupIdentityService {

    @Autowired
    @Qualifier("translatorGroupIdentityServiceImplV1")
    private TranslatorGroupIdentityService translatorGroupIdentityService;

    @Override
    public ResponseEntity<Object> update(TranslatorGroupIdentityInDTO translatorGroupIdentityInDTO) {

        Optional<TranslatorGroupIdentity> rs = this.translatorGroupIdentityService.findByIdWithAllRelationshipsLoadedLazily(translatorGroupIdentityInDTO.getId());

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Translator group identity with ID " + translatorGroupIdentityInDTO.getId() + " is not found.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        TranslatorGroupIdentity translatorGroupIdentity = rs.get();

        translatorGroupIdentity.setNickname(translatorGroupIdentityInDTO.getNickname());
        translatorGroupIdentity.setAvatarLink(translatorGroupIdentityInDTO.getAvatarLink());
        translatorGroupIdentity.setDob(translatorGroupIdentityInDTO.getDob());
        translatorGroupIdentity.setRoleInNumber(translatorGroupIdentityInDTO.getRoleInNumber());
        translatorGroupIdentity.setRole(translatorGroupIdentityInDTO.getRole());
        translatorGroupIdentity.setContactLink(translatorGroupIdentityInDTO.getContactLink());
        translatorGroupIdentity.setEmail(translatorGroupIdentityInDTO.getEmail());
        translatorGroupIdentity.setPhoneNumber(translatorGroupIdentityInDTO.getPhoneNumber());
        translatorGroupIdentity.setGender(translatorGroupIdentityInDTO.getGender());
        translatorGroupIdentity.setFakeId(StringUtils.purify(translatorGroupIdentity.getNickname()));

        translatorGroupIdentity = this.translatorGroupIdentityService.saveOrUpdate(translatorGroupIdentity);

        HttpHeaders headers = new HttpHeaders();

        headers.add("id", translatorGroupIdentity.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }

}


































