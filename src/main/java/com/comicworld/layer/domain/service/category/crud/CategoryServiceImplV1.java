package com.comicworld.layer.domain.service.category.crud;

import com.comicworld.layer.domain.dao.category.CategoryDAO;
import com.comicworld.layer.domain.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service("categoryServiceImplV1")
@Transactional
public class CategoryServiceImplV1 implements CategoryService {

    @Autowired
    private CategoryDAO dao;

    @Override
    public Category saveOrUpdate(Category category) {
        return this.dao.save(category);
    }

    @Override
    public List<Category> saveOrUpdateAll(Collection<Category> categories) {
        return this.dao.saveAll(categories);
    }

    @Override
    public List<Category> findByIdIn(Collection<Long> ids) {
        return this.dao.findByIdIn(ids);
    }

    @Override
    public List<Category> findByComicId(Long id) {
        return this.dao.findByComicId(id);
    }

    @Override
    public List<Category> searchByKeyword(String keyword, Integer limit) {
        return this.dao.searchByKeyword(keyword, limit);
    }

    @Override
    public void createFullTextIndexByColumn(String column) {
        this.dao.createFullTextIndexByColumn(column);
    }

    @Override
    public List<Category> findAll() {
        return this.dao.findAll();
    }

}
