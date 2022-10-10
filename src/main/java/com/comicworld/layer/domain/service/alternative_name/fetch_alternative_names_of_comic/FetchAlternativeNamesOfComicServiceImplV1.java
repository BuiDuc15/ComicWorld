package com.comicworld.layer.domain.service.alternative_name.fetch_alternative_names_of_comic;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameOutDTO;
import com.comicworld.layer.domain.entity.AlternativeName;
import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fetchAlternativeNamesOfComicServiceImplV1")
public class FetchAlternativeNamesOfComicServiceImplV1 implements FetchAlternativeNamesOfComicService {

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Override
    public ResponseEntity<Object> fetch(Long comicId) {
        List<AlternativeName> alternativeNames = this.alternativeNameService.findByComicId(comicId);

        List<AlternativeNameOutDTO> alternativeNamesOutDTO = alternativeNames.stream()
                .map(alternativeName -> new AlternativeNameOutDTO(alternativeName))
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                alternativeNamesOutDTO
        ), HttpStatus.OK);
    }
}
