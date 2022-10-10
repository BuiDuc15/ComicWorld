package com.comicworld.layer.domain.service.chapter_storage.create_chapter_storage;

import com.comicworld.layer.domain.dto.chapter.ChapterStorageInDTO;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.entity.chapter.ChapterStorage;
import com.comicworld.layer.domain.entity.chapter.Source;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.chapter.crud.ChapterService;
import com.comicworld.layer.domain.service.chapter_storage.crud.ChapterStorageService;
import com.comicworld.layer.domain.service.source.SourceService;
import com.comicworld.utils.Env;
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
import java.util.Optional;

@Service("createChapterStorageServiceImplV1")
@Transactional
public class CreateChapterStorageServiceImplV1 implements CreateChapterStorageService {

    @Autowired
    @Qualifier("chapterStorageServiceImplV1")
    private ChapterStorageService chapterStorageService;

    @Autowired
    @Qualifier("chapterServiceImplV1")
    private ChapterService chapterService;

    @Autowired
    @Qualifier("sourceServiceImplV1")
    private SourceService sourceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Object> create(List<ChapterStorageInDTO> chapterStoragesInDTO) {

        Long chapterId = chapterStoragesInDTO.get(0).getChapterId();

        String storageType = chapterStoragesInDTO.get(0).getStorageType();

        Optional<Chapter> findingChapterRs = this.chapterService.findByIdWithAllRelationshipsLoadedLazily(chapterId);

        if(findingChapterRs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Chapter with ID " + chapterId + " does not exits.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Optional<Source> findingSourceRs = this.sourceService.findByStorageTypeWithAllRelationshipsLoadedLazily(storageType);

        if(findingSourceRs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Source with storage type " + storageType + " does not exits.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        List<ChapterStorage> chapterStorages = new ArrayList();

        for(ChapterStorageInDTO chapterStorageInDTO : chapterStoragesInDTO) {
            ChapterStorage chapterStorage = chapterStorageInDTO.toChapterStorage();
            chapterStorage.setChapter(findingChapterRs.get());
            chapterStorage.setSource(findingSourceRs.get());
            chapterStorages.add(chapterStorage);
        }

        chapterStorages = this.chapterStorageService.saveOrUpdateAll(chapterStorages);

        List<Long> chapterStorageIds = new ArrayList<>();

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

        return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.CREATED);
    }
}
