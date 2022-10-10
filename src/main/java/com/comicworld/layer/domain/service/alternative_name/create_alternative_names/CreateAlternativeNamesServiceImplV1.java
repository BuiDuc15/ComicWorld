package com.comicworld.layer.domain.service.alternative_name.create_alternative_names;

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

@Service("createAlternativeNamesServiceImplV1")
@Transactional
public class CreateAlternativeNamesServiceImplV1 implements CreateAlternativeNamesService {

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Object> create(List<AlternativeNameInDTO> alternativeNamesInDTO) {

        List<AlternativeName> alternativeNames = alternativeNamesInDTO.stream()
                .map(alternativeNameInDTO -> alternativeNameInDTO.toAlternativeName())
                .collect(Collectors.toList());

        alternativeNames.forEach(alternativeName -> {
            String cleanedName = alternativeName.getName().trim().replaceAll("  *", " ");
            alternativeName.setName(cleanedName);
        });

        alternativeNames = this.alternativeNameService.saveOrUpdateAll(alternativeNames);

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.set("ids", this.objectMapper.writeValueAsString(
                    alternativeNames.stream()
                            .map(alternativeName -> alternativeName.getId().toString())
                            .collect(Collectors.toList())
            ));
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.CREATED);
    }

}










































