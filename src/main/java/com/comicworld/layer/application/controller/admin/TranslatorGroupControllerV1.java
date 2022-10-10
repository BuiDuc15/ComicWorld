package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupInDTO;
import com.comicworld.layer.domain.service.translator_group.create_translator_group.CreateTranslatorGroupService;
import com.comicworld.layer.domain.service.translator_group.fetch_translator_group_info_by_id.FetchTranslatorGroupInfoByIdService;
import com.comicworld.layer.domain.service.translator_group.fetch_translator_groups_of_comic.FetchTranslatorGroupsOfComicService;
import com.comicworld.layer.domain.service.translator_group.search.TranslatorGroupSearchService;
import com.comicworld.layer.domain.service.translator_group.update_translator_group.UpdateTranslatorGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class TranslatorGroupControllerV1 {

    @Autowired
    @Qualifier("createTranslatorGroupServiceImplV1")
    private CreateTranslatorGroupService createTranslatorGroupService;

    @Autowired
    @Qualifier("updateTranslatorGroupServiceImplV1")
    private UpdateTranslatorGroupService updateTranslatorGroupService;

    @Autowired
    @Qualifier("fetchTranslatorGroupInfoByIdServiceImplV1")
    private FetchTranslatorGroupInfoByIdService fetchTranslatorGroupInfoByIdService;

    @Autowired
    @Qualifier("translatorGroupSearchServiceImplV1")
    private TranslatorGroupSearchService translatorGroupSearchService;

    @Autowired
    @Qualifier("fetchTranslatorGroupsOfComicServiceImplV1")
    private FetchTranslatorGroupsOfComicService fetchTranslatorGroupsOfComicService;

    @PostMapping(path = "/v1/translator-groups")
    public ResponseEntity<Object> createTranslatorGroup(@RequestBody TranslatorGroupInDTO translatorGroupInDTO) {
        return this.createTranslatorGroupService.create(translatorGroupInDTO);
    }

    @PutMapping(path = "/v1/translator-groups")
    public ResponseEntity<Object> updateTranslatorGroup(@RequestBody TranslatorGroupInDTO translatorGroupInDTO) {
        return this.updateTranslatorGroupService.update(translatorGroupInDTO);
    }

    @GetMapping(path = "/v1/translator-groups/{translatorGroupId}")
    public ResponseEntity<Object> fetchTranslatorGroupInfo(@PathVariable("translatorGroupId") Long translatorGroupId) {
        return this.fetchTranslatorGroupInfoByIdService.fetch(translatorGroupId);
    }

    @GetMapping(path = "/v1/translator-groups/search")
    public ResponseEntity<Object> searchByKeyword(@RequestParam("keyword") String keyword) {
        return this.translatorGroupSearchService.search(keyword);
    }

    @GetMapping(path = "/v1/comics/{comicId}/translator-groups")
    public ResponseEntity<Object> fetchCategoriesOfComic(@PathVariable("comicId") Long comicId) {
        return this.fetchTranslatorGroupsOfComicService.fetch(comicId);
    }

}


























