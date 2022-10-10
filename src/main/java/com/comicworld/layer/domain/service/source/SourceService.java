package com.comicworld.layer.domain.service.source;

import com.comicworld.layer.domain.entity.chapter.Source;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SourceService {

    public Source saveOrUpdate(Source source);

    public List<Source> saveOrUpdateAll(Collection<Source> sources);

    public Optional<Source> findByStorageTypeWithAllRelationshipsLoadedLazily(String storageType);

}
