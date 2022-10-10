package com.comicworld.layer.domain.service.translator_group_identity.delete_translator_group_identity;

import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.translator_group_identity.crud.TranslatorGroupIdentityService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("deleteTranslatorGroupIdentityServiceImplV1")
@Transactional
public class DeleteTranslatorGroupIdentityServiceImplV1 implements DeleteTranslatorGroupIdentityService {

    @Autowired
    @Qualifier("translatorGroupIdentityServiceImplV1")
    private TranslatorGroupIdentityService translatorGroupIdentityService;

    @Override
    public ResponseEntity<Object> delete(Long translatorGroupIdentityId) {

        Boolean isExisted = this.translatorGroupIdentityService.existsById(translatorGroupIdentityId);

        if(!isExisted)
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Translator group identity with ID " + translatorGroupIdentityId + " is not found.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        this.translatorGroupIdentityService.deleteById(translatorGroupIdentityId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}



































