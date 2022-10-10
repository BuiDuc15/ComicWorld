package com.comicworld.layer.domain.service.comic.crud;

import com.comicworld.layer.domain.dao.comic.ComicDAO;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("comicServiceImplV1")
@Transactional
public class ComicServiceImplV1 implements ComicService {

    @Autowired
    private ComicDAO dao;

    @Override
    public Comic saveOrUpdate(Comic comic) {
        return this.dao.save(comic);
    }

    @Override
    public Optional<Comic> findByIdWithChaptersLoadedLazily(Long id) {
        return this.dao.findByIdWithChaptersLoadedLazily(id);
    }

    @Override
    public Optional<Comic> findByIdWithAllRelationshipsLoadedLazily(Long id) {
        return this.dao.findByIdWithAllRelationshipsLoadedLazily(id);
    }

    @Override
    public Optional<Comic> findByIdWithAllRelationshipsLoadedEagerly(Long id) {
        return this.dao.findByIdWithAllRelationshipsLoadedEagerly(id);
    }

    @Override
    public Optional<Comic> findByFakeIdWithAllRelationshipsLoadedEagerly(String fakeId) {
        return this.dao.findByFakeIdWithAllRelationshipsLoadedEagerly(fakeId);
    }

    @Override
    public void updateLoveOfComicById(Long id, String action) throws OptimisticLockException, NoResultException {
        this.dao.updateLoveOfComicById(id, action);
    }

    @Override
    public void updateDislikeOfComicById(Long id, String action) throws OptimisticLockException, NoResultException {
        this.dao.updateDislikeOfComicById(id, action);
    }

    @Override
    public void updateViewOfComicById(Long id) throws OptimisticLockException, NoResultException {
        this.dao.updateViewOfComicById(id);
    }

    @Override
    public List<Comic> findByAlternativeNameIdsWithAllRelationshipsLoadedLazily(Collection<Long> alternativeNameIds) {
        return this.dao.findByAlternativeNameIdsWithAllRelationshipsLoadedLazily(alternativeNameIds);
    }

    @Override
    public List<Comic> findByAlternativeNameIdsInWithChaptersLoadedEagerly(Collection<Long> alternativeNameIds) {
        return this.dao.findByAlternativeNameIdsInWithChaptersLoadedEagerly(alternativeNameIds);
    }

    @Override
    public PageModel findByCategoryFakeIdWithChaptersLoadedEagerly(Map<String, Object> params) {
        return this.dao.findByCategoryFakeIdWithChaptersLoadedEagerly(params);
    }

    @Override
    public List<Comic> findTop10MostViewedComicsWithChaptersLoadedEagerly() {
        return this.dao.findTop10MostViewedComicsWithChaptersLoadedEagerly();
    }

    @Override
    public List<Comic> findTop10MostPopularComicsWithChaptersLoadedEagerly() {
        return this.dao.findTop10MostPopularComicsWithChaptersLoadedEagerly();
    }

    @Override
    public List<Comic> findTop10LatestComicsWithChaptersLoadedEagerly() {
        return this.dao.findTop10LatestComicsWithChaptersLoadedEagerly();
    }

    @Override
    public List<Comic> findTop15LastUpdatedComicsWithChaptersLoadedEagerly() {
        return this.dao.findTop15LastUpdatedComicsWithChaptersLoadedEagerly();
    }

}
