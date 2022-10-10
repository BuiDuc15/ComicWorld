package com.comicworld.layer.domain.service.source;

import com.comicworld.layer.domain.dao.SourceDAO;
import com.comicworld.layer.domain.entity.chapter.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("sourceServiceImplV1")
@Transactional
public class SourceServiceImplV1 implements SourceService {

    @Autowired
    private SourceDAO dao;

    @Override
    public Source saveOrUpdate(Source source) {
        return this.dao.save(source);
    }

    @Override
    public List<Source> saveOrUpdateAll(Collection<Source> sources) {
        return this.dao.saveAll(sources);
    }

    @Override
    public Optional<Source> findByStorageTypeWithAllRelationshipsLoadedLazily(String storageType) {
        return this.dao.findByStorageTypeWithAllRelationshipsLoadedLazily(storageType);
    }

}
