package com.comicworld.layer.domain.dao.alternative_name;

import com.comicworld.layer.domain.entity.AlternativeName;

import java.util.List;
import java.util.Map;

public interface CustomAlternativeNameDAO {

    public void createFullTextIndexByColumn(String column);

    public List<AlternativeName> searchByName(Map<String, Object> params);

    public List<AlternativeName> searchFullByName(Map<String, Object> params);

}
