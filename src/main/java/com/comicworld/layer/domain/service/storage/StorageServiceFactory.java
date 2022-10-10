package com.comicworld.layer.domain.service.storage;

import com.comicworld.utils.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StorageServiceFactory {

    @Autowired
    @Qualifier("s3StorageServiceImplV1")
    private StorageService s3StorageService;

    public StorageService getStorageServiceBasedOnType(String storageType) {
        if(storageType.equalsIgnoreCase(Env.S3_STORAGE_TYPE))
            return this.s3StorageService;
        return null;
    }

}
