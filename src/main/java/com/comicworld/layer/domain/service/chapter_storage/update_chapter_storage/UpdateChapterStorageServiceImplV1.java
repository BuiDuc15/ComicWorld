package com.comicworld.layer.domain.service.chapter_storage.update_chapter_storage;

import com.comicworld.layer.domain.dto.chapter.ChapterStorageInDTO;
import com.comicworld.layer.domain.entity.chapter.ChapterStorage;
import com.comicworld.layer.domain.service.chapter_storage.crud.ChapterStorageService;
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

import java.util.ArrayList;
import java.util.List;

@Service("updateChapterStorageServiceImplV1")
@Transactional
public class UpdateChapterStorageServiceImplV1 implements UpdateChapterStorageService {

    @Autowired
    @Qualifier("chapterStorageServiceImplV1")
    private ChapterStorageService chapterStorageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Object> update(List<ChapterStorageInDTO> chapterStoragesInDTO) {
        List<Long> chapterStorageIds = new ArrayList<>();

        chapterStoragesInDTO.forEach(chapterStorageInDTO -> chapterStorageIds.add(chapterStorageInDTO.getId()));

        List<ChapterStorage> chapterStorages = this.chapterStorageService.findByIdInWithAllRelationshipsLoadedLazily(chapterStorageIds);

        for(int i = 0; i < chapterStorages.size(); ++i) {
            chapterStorages.get(i).setPlaceOrder(chapterStoragesInDTO.get(i).getPlaceOrder());
            chapterStorages.get(i).setLink(chapterStoragesInDTO.get(i).getLink());
        }

        chapterStorages = this.chapterStorageService.saveOrUpdateAll(chapterStorages);

        chapterStorageIds.clear();

        for(ChapterStorage chapterStorage : chapterStorages) {
            chapterStorageIds.add(chapterStorage.getId());
        }

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.set("ids", this.objectMapper.writeValueAsString(chapterStorageIds));
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }

}





































