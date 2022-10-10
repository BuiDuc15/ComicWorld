package com.comicworld.layer.domain.service.alternative_name.create_alternative_names;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameInDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CreateAlternativeNamesService {

    public ResponseEntity<Object> create(List<AlternativeNameInDTO> alternativeNamesInDTO);

}
