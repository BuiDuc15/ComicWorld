package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.dto.chapter.ChapterStorageInDTO;
import com.comicworld.layer.domain.model.ChapterModel;
import com.comicworld.layer.domain.service.chapter_storage.create_chapter_storage.CreateChapterStorageService;
import com.comicworld.layer.domain.service.chapter_storage.delete_chapter_storage.DeleteChapterStorageService;
import com.comicworld.layer.domain.service.chapter_storage.fetch_chapter_storage_batch.FetchChapterStorageBatchService;
import com.comicworld.layer.domain.service.chapter_storage.update_chapter_storage.UpdateChapterStorageService;
import com.comicworld.layer.domain.service.chapter_storage.upload_chapter.UploadChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class ChapterStorageControllerV1 {

    @Autowired
    @Qualifier("createChapterStorageServiceImplV1")
    private CreateChapterStorageService createChapterStorageService;

    @Autowired
    @Qualifier("updateChapterStorageServiceImplV1")
    private UpdateChapterStorageService updateChapterStorageService;

    @Autowired
    @Qualifier("deleteChapterStorageServiceImplV1")
    private DeleteChapterStorageService deleteChapterStorageService;

    @Autowired
    @Qualifier("uploadChapterServiceImplV1")
    private UploadChapterService uploadChapterService;

    @Autowired
    @Qualifier("fetchChapterStorageBatchServiceImplV1")
    private FetchChapterStorageBatchService fetchChapterStorageBatchService;

    @GetMapping(path = "/v1/chapter-storages/batch")
    public ResponseEntity<Object> fetchBatch(@RequestParam("ids") List<Long> ids) {
        return this.fetchChapterStorageBatchService.fetch(ids);
    }

    @PostMapping(path = "/v1/comics/{comicId}/chapters/{chapterId}/chapter-storages/batch")
    public ResponseEntity<Object> createChapterStorages(@RequestBody List<ChapterStorageInDTO> chapterStorages) {
        return this.createChapterStorageService.create(chapterStorages);
    }

    @PostMapping(path = "/v1/chapter-storages/upload")
    public ResponseEntity<Object> uploadChapterStorage(ChapterModel chapterModel) {
        return this.uploadChapterService.upload(chapterModel);
    }

    @PutMapping(path = "/v1/comics/{comicId}/chapters/{chapterId}/chapter-storages/batch")
    public ResponseEntity<Object> updateChapterStorages(@RequestBody List<ChapterStorageInDTO> chapterStorages) {
        return this.updateChapterStorageService.update(chapterStorages);
    }

    @DeleteMapping(path = "/v1/chapter-storages/{chapterStorageId}")
    public ResponseEntity<Object> deleteChapterStorage(@PathVariable("chapterStorageId") Long id) {
        return this.deleteChapterStorageService.delete(id);
    }

}


















































