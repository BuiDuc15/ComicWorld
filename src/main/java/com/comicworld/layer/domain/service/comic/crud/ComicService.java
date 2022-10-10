package com.comicworld.layer.domain.service.comic.crud;

import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.model.PageModel;

import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ComicService {

    public Comic saveOrUpdate(Comic comic);

    public Optional<Comic> findByIdWithChaptersLoadedLazily(Long id);

    public Optional<Comic> findByIdWithAllRelationshipsLoadedLazily(Long id);

    public Optional<Comic> findByIdWithAllRelationshipsLoadedEagerly(Long id);

    public Optional<Comic> findByFakeIdWithAllRelationshipsLoadedEagerly(String fakeId);

    public void updateLoveOfComicById(Long id, String action) throws OptimisticLockException, NoResultException;

    public void updateDislikeOfComicById(Long id, String action) throws OptimisticLockException, NoResultException;

    public void updateViewOfComicById(Long id) throws OptimisticLockException, NoResultException;

    public List<Comic> findByAlternativeNameIdsWithAllRelationshipsLoadedLazily(Collection<Long> alternativeNameIds);

    public List<Comic> findByAlternativeNameIdsInWithChaptersLoadedEagerly(Collection<Long> alternativeNameIds);

    public PageModel findByCategoryFakeIdWithChaptersLoadedEagerly(Map<String, Object> params);

    public List<Comic> findTop10MostViewedComicsWithChaptersLoadedEagerly();

    public List<Comic> findTop10MostPopularComicsWithChaptersLoadedEagerly();

    public List<Comic> findTop10LatestComicsWithChaptersLoadedEagerly();

    public List<Comic> findTop15LastUpdatedComicsWithChaptersLoadedEagerly();



}
