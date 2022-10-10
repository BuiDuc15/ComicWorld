package com.comicworld.layer.domain.service.chapter_storage.fetch_chapter_storage_batch;

import com.comicworld.layer.domain.dto.chapter.ChapterStorageOutDTO;
import com.comicworld.layer.domain.entity.chapter.ChapterStorage;
import com.comicworld.layer.domain.service.chapter_storage.crud.ChapterStorageService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fetchChapterStorageBatchServiceImplV1")
public class FetchChapterStorageBatchServiceImplV1 implements FetchChapterStorageBatchService {

    @Autowired
    @Qualifier("chapterStorageServiceImplV1")
    private ChapterStorageService chapterStorageService;

    @Override
    public ResponseEntity<Object> fetch(List<Long> ids) {

        List<ChapterStorage> chapterStorages = this.chapterStorageService.findByIdInWithAllRelationshipsLoadedLazily(ids);

        List<ChapterStorageOutDTO> chapterStoragesOutDTO = chapterStorages.stream()
                .map(chapterStorage -> new ChapterStorageOutDTO(chapterStorage))
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(chapterStoragesOutDTO), HttpStatus.OK);
    }
}
