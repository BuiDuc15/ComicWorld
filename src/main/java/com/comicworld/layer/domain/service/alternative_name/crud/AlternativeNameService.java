package com.comicworld.layer.domain.service.alternative_name.crud;

import com.comicworld.layer.domain.entity.AlternativeName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AlternativeNameService {

    public AlternativeName saveOrUpdate(AlternativeName alternativeName);

    public List<AlternativeName> saveOrUpdateAll(Collection<AlternativeName> alternativeNames);

    public List<AlternativeName> findByIdIn(Collection<Long> ids);

    public List<AlternativeName> findByComicId(Long id);

    public void deleteAll(Collection<AlternativeName> alternativeNames);

    public void deleteById(Long id);

    public List<AlternativeName> searchByName(String keyword, Integer limit);

    public void createFullTextIndexByColumn(String column);

    public List<AlternativeName> searchByName(Map<String, Object> params);

    public List<AlternativeName> searchFullByName(Map<String, Object> params);

}
