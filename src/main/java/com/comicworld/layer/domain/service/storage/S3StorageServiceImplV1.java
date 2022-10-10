package com.comicworld.layer.domain.service.storage;

import com.comicworld.layer.domain.model.storage_resource.ResourceModel;
import com.comicworld.layer.domain.model.storage_resource.S3ResourceModel;
import com.comicworld.layer.domain.service.aws_s3.AWSS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("s3StorageServiceImplV1")
public class S3StorageServiceImplV1 implements StorageService {

    @Autowired
    @Qualifier("awsS3ServiceImplV1")
    private AWSS3Service awss3Service;

    @Override
    public List<String> uploadResource(ResourceModel resourceModel) {

        S3ResourceModel s3ResourceModel = (S3ResourceModel) resourceModel;

        return this.awss3Service.uploadFilesToPath(s3ResourceModel.getFiles(), s3ResourceModel.getPath());
    }

}













































