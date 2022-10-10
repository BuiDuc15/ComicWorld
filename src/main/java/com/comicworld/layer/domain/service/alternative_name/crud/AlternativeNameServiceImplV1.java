package com.comicworld.layer.domain.service.alternative_name.crud;

import com.comicworld.layer.domain.dao.alternative_name.AlternativeNameDAO;
import com.comicworld.layer.domain.entity.AlternativeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("alternativeNameServiceImplV1")
@Transactional
public class AlternativeNameServiceImplV1 implements AlternativeNameService {

    @Autowired
    private AlternativeNameDAO dao;

    @Override
    public AlternativeName saveOrUpdate(AlternativeName alternativeName) {
        return this.dao.save(alternativeName);
    }

    @Override
    public List<AlternativeName> saveOrUpdateAll(Collection<AlternativeName> alternativeNames) {
        return this.dao.saveAll(alternativeNames);
    }

    @Override
    public List<AlternativeName> findByIdIn(Collection<Long> ids) {
        return this.dao.findByIdIn(ids);
    }

    @Override
    public List<AlternativeName> findByComicId(Long id) {
        return this.dao.findByComicId(id);
    }

    @Override
    public void deleteAll(Collection<AlternativeName> alternativeNames) {
        this.dao.deleteAll(alternativeNames);
    }

    @Override
    public void deleteById(Long id) {
        this.dao.deleteById(id);
    }

    @Override
    public List<AlternativeName> searchByName(String keyword, Integer limit) {
        return this.dao.searchByKeyword(keyword, limit);
    }

    @Override
    public void createFullTextIndexByColumn(String column) {
        this.dao.createFullTextIndexByColumn(column);
    }

    @Override
    public List<AlternativeName> searchByName(Map<String, Object> params) {
        return this.dao.searchByName(params);
    }

    @Override
    public List<AlternativeName> searchFullByName(Map<String, Object> params) {
        return this.dao.searchFullByName(params);
    }

}
