package com.comicworld.layer.domain.service.storage;

import com.comicworld.layer.domain.model.storage_resource.ResourceModel;

import java.util.List;

public interface StorageService {

    public List<String> uploadResource(ResourceModel resourceModel);

}
