package com.comicworld.layer.domain.service.translator_group.create_translator_group;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupInDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
import com.comicworld.utils.StringUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("createTranslatorGroupServiceImplV1")
@Transactional
public class CreateTranslatorGroupServiceImplV1 implements CreateTranslatorGroupService {

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

    @Override
    public ResponseEntity<Object> create(TranslatorGroupInDTO translatorGroupInDTO) {

        TranslatorGroup translatorGroup = translatorGroupInDTO.toTranslatorGroup();

        translatorGroup.setComplaints(0l);
        translatorGroup.setShoutOuts(0l);
        translatorGroup.setFakeId(StringUtils.purify(translatorGroup.getName()));

        translatorGroup = this.translatorGroupService.saveOrUpdate(translatorGroup);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", translatorGroup.getId().toString());

        return new ResponseEntity<>(
                ResponseBodyFactoryV1.createdResponseBody(),
                headers,
                HttpStatus.CREATED
        );
    }

}
