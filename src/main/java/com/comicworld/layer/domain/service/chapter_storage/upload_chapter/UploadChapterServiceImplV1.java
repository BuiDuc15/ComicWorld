package com.comicworld.layer.domain.service.chapter_storage.upload_chapter;

import com.comicworld.layer.domain.model.ChapterModel;
import com.comicworld.layer.domain.model.storage_resource.ResourceModel;
import com.comicworld.layer.domain.service.resource_model.ResourceModelMapper;
import com.comicworld.layer.domain.service.storage.StorageService;
import com.comicworld.layer.domain.service.storage.StorageServiceFactory;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("uploadChapterServiceImplV1")
public class UploadChapterServiceImplV1 implements UploadChapterService {

    @Autowired
    private StorageServiceFactory storageServiceFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<Object> upload(ChapterModel chapterModel) {

        StorageService storageService = this.storageServiceFactory.getStorageServiceBasedOnType(chapterModel.getStorageType());

        ResourceModel resourceModel = ResourceModelMapper.mapChapterModelToResourceModelBasedOnType(chapterModel);

        List<String> links = storageService.uploadResource(resourceModel);

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.set("links", this.objectMapper.writeValueAsString(links));
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.CREATED);
    }

}











































