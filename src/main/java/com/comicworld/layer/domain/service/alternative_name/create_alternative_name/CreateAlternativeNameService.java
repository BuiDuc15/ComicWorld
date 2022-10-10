package com.comicworld.layer.domain.service.alternative_name.create_alternative_name;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameInDTO;
import org.springframework.http.ResponseEntity;

public interface CreateAlternativeNameService {

    public ResponseEntity<Object> create(AlternativeNameInDTO alternativeNameInDTO);

}
