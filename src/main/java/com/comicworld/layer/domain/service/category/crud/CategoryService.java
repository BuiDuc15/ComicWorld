package com.comicworld.layer.domain.service.category.crud;

import com.comicworld.layer.domain.entity.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryService {

    public Category saveOrUpdate(Category category);

    public List<Category> saveOrUpdateAll(Collection<Category> categories);

    public List<Category> findByIdIn(Collection<Long> ids);

    public List<Category> findByComicId(Long id);

    public List<Category> searchByKeyword(String keyword, Integer limit);

    public void createFullTextIndexByColumn(String column);

    public List<Category> findAll();

}
