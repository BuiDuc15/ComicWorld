package com.comicworld.layer.domain.service.alternative_name.update_alternative_names;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameInDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UpdateAlternativeNamesService {

    public ResponseEntity<Object> update(List<AlternativeNameInDTO> alternativeNamesInDTO);

}
