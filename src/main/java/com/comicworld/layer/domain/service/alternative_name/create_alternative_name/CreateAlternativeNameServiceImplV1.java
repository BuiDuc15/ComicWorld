package com.comicworld.layer.domain.service.alternative_name.create_alternative_name;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameInDTO;
import com.comicworld.layer.domain.entity.AlternativeName;
import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("createAlternativeNameServiceImplV1")
@Transactional
public class CreateAlternativeNameServiceImplV1 implements CreateAlternativeNameService {

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Override
    public ResponseEntity<Object> create(AlternativeNameInDTO alternativeNameInDTO) {

        AlternativeName alternativeName = alternativeNameInDTO.toAlternativeName();

        String cleanedName = alternativeName.getName().trim().replaceAll("  *", " ");

        alternativeName.setName(cleanedName);

        alternativeName = this.alternativeNameService.saveOrUpdate(alternativeName);

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", alternativeName.getId().toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.CREATED);
    }

}

































