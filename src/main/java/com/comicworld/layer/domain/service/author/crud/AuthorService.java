package com.comicworld.layer.domain.service.author.crud;

import com.comicworld.layer.domain.entity.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorService {

    public Author saveOrUpdate(Author author);

    public List<Author> saveOrUpdateAll(Collection<Author> authors);

    public List<Author> findByIdIn(Collection<Long> ids);

    public List<Author> findByComicId(Long id);

    public List<Author> searchByKeyword(String keyword, Integer limit);

    public void createFullTextIndexByColumn(String column);

}
