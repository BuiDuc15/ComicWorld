package com.comicworld.layer.domain.service.alternative_name.update_alternative_names;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameInDTO;
import com.comicworld.layer.domain.entity.AlternativeName;
import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("updateAlternativeNamesServiceImplV1")
@Transactional
public class UpdateAlternativeNamesServiceImplV1 implements UpdateAlternativeNamesService {

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Object> update(List<AlternativeNameInDTO> alternativeNamesInDTO) {
        List<AlternativeName> alternativeNames = this.alternativeNameService.findByIdIn(
            alternativeNamesInDTO.stream()
                    .map(alternativeNameInDTO -> alternativeNameInDTO.getId())
                    .collect(Collectors.toList())
        );

        for(int i = 0; i < alternativeNames.size(); ++i) {
            alternativeNames.get(i).setName(alternativeNamesInDTO.get(i).getName());
        }

        HttpHeaders headers = new HttpHeaders();

        alternativeNames = this.alternativeNameService.saveOrUpdateAll(alternativeNames);

        try {
            String alternativeNameIds = this.objectMapper.writeValueAsString(
                alternativeNames.stream()
                        .map(alternativeName -> alternativeName.getId())
                        .collect(Collectors.toList())
            );

            headers.set("ids", alternativeNameIds);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }
}


















































