package com.comicworld.layer.domain.service.alternative_name.delete_alternative_name;

import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("deleteAlternativeNameServiceImplV1")
@Transactional
public class DeleteAlternativeNameServiceImplV1 implements DeleteAlternativeNameService {

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Override
    public ResponseEntity<Object> delete(Long id) {

        this.alternativeNameService.deleteById(id);

        return new ResponseEntity<>(ResponseBodyFactoryV1.noContentResponseBody(), HttpStatus.NO_CONTENT);
    }
}
