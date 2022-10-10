package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.dto.alternative_name.AlternativeNameInDTO;
import com.comicworld.layer.domain.service.alternative_name.create_alternative_names.CreateAlternativeNamesService;
import com.comicworld.layer.domain.service.alternative_name.delete_alternative_name.DeleteAlternativeNameService;
import com.comicworld.layer.domain.service.alternative_name.fetch_alternative_names_of_comic.FetchAlternativeNamesOfComicService;
import com.comicworld.layer.domain.service.alternative_name.update_alternative_names.UpdateAlternativeNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AlternativeNameControllerV1 {

    @Autowired
    @Qualifier("createAlternativeNamesServiceImplV1")
    private CreateAlternativeNamesService createAlternativeNamesService;

    @Autowired
    @Qualifier("fetchAlternativeNamesOfComicServiceImplV1")
    private FetchAlternativeNamesOfComicService fetchAlternativeNamesOfComicService;

    @Autowired
    @Qualifier("deleteAlternativeNameServiceImplV1")
    private DeleteAlternativeNameService deleteAlternativeNameService;

    @Autowired
    @Qualifier("updateAlternativeNamesServiceImplV1")
    private UpdateAlternativeNamesService updateAlternativeNamesService;

    @PostMapping(path = "/v1/alternative-names/batch")
    public ResponseEntity<Object> createAlternativeNames(@RequestBody List<AlternativeNameInDTO> alternativeNamesInDTO) {
        return this.createAlternativeNamesService.create(alternativeNamesInDTO);
    }

    @GetMapping(path = "/v1/comics/{comicId}/alternative-names")
    public ResponseEntity<Object> fetchAlternativeNamesOfComic(@PathVariable("comicId") Long comicId) {
        return this.fetchAlternativeNamesOfComicService.fetch(comicId);
    }

    @DeleteMapping(path = "/v1/alternative-names/{id}")
    public ResponseEntity<Object> deleteAlternativeName(@PathVariable("id") Long id) {
        return this.deleteAlternativeNameService.delete(id);
    }

    @PutMapping(path = "/v1/alternative-names/batch")
    public ResponseEntity<Object> updateAlternativeNames(@RequestBody List<AlternativeNameInDTO> alternativeNamesInDTO) {
        return this.updateAlternativeNamesService.update(alternativeNamesInDTO);
    }


}
