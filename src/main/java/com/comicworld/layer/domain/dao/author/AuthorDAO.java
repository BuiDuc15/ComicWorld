package com.comicworld.layer.domain.dao.author;

import com.comicworld.layer.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long>, CustomAuthorDAO {

    @Query("SELECT a FROM authors a LEFT JOIN FETCH a.comics c WHERE a.id IN :ids")
    public List<Author> findByIdIn(@Param("ids") Collection<Long> ids);

    @Query("SELECT a FROM authors a JOIN a.comics c WHERE c.id = :id")
    public List<Author> findByComicId(@Param("id") Long id);

    @Query(value = "SELECT * FROM authors a WHERE MATCH(a.name) AGAINST (?1 IN BOOLEAN MODE) LIMIT ?2",
            nativeQuery = true)
    public List<Author> searchByKeyword(String keyword, Integer limit);

}
