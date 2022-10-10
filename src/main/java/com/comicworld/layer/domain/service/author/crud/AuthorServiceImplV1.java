package com.comicworld.layer.domain.service.author.crud;

import com.comicworld.layer.domain.dao.author.AuthorDAO;
import com.comicworld.layer.domain.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service("authorServiceImplV1")
@Transactional
public class AuthorServiceImplV1 implements AuthorService {

    @Autowired
    private AuthorDAO dao;

    @Override
    public Author saveOrUpdate(Author author) {
        return this.dao.save(author);
    }

    @Override
    public List<Author> saveOrUpdateAll(Collection<Author> authors) {
        return this.dao.saveAll(authors);
    }

    @Override
    public List<Author> findByIdIn(Collection<Long> ids) {
        return this.dao.findByIdIn(ids);
    }

    @Override
    public List<Author> findByComicId(Long id) {
        return this.dao.findByComicId(id);
    }

    @Override
    public List<Author> searchByKeyword(String keyword, Integer limit) {
        return this.dao.searchByKeyword(keyword, limit);
    }

    @Override
    public void createFullTextIndexByColumn(String column) {
        this.dao.createFullTextIndexByColumn(column);
    }


}
