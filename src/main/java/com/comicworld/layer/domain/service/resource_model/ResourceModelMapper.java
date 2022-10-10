package com.comicworld.layer.domain.service.resource_model;

import com.comicworld.layer.domain.model.ChapterModel;
import com.comicworld.layer.domain.model.storage_resource.ResourceModel;
import com.comicworld.layer.domain.model.storage_resource.S3ResourceModel;
import com.comicworld.utils.Env;

public class ResourceModelMapper {

    public static ResourceModel mapChapterModelToResourceModelBasedOnType(ChapterModel chapterModel) {

        if(chapterModel.getStorageType().equalsIgnoreCase(Env.S3_STORAGE_TYPE)) {
            S3ResourceModel s3ResourceModel = new S3ResourceModel();
            s3ResourceModel.setFiles(chapterModel.getFiles());
            s3ResourceModel.setPath(chapterModel.getPath());

            return s3ResourceModel;
        }

        return null;
    }

}
