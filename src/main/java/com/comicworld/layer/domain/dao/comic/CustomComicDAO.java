package com.comicworld.layer.domain.dao.comic;

import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.model.PageModel;

import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import java.util.List;
import java.util.Map;

public interface CustomComicDAO {

    public PageModel findByCategoryFakeIdWithChaptersLoadedEagerly(Map<String, Object> params);

    public void updateLoveOfComicById(Long id, String action) throws OptimisticLockException, NoResultException;

    public void updateDislikeOfComicById(Long id, String action) throws OptimisticLockException, NoResultException;

    public void updateViewOfComicById(Long id) throws OptimisticLockException, NoResultException;

    public List<Comic> findTop10MostViewedComicsWithChaptersLoadedEagerly();

    public List<Comic> findTop10MostPopularComicsWithChaptersLoadedEagerly();

    public List<Comic> findTop15LastUpdatedComicsWithChaptersLoadedEagerly();

    public List<Comic> findTop10LatestComicsWithChaptersLoadedEagerly();

}
