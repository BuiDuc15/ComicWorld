package com.comicworld.layer.domain.dao.category;

import com.comicworld.layer.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long>, CustomCategoryDAO {

    @Query("SELECT c FROM categories c LEFT JOIN FETCH c.comics WHERE c.id IN :ids")
    public List<Category> findByIdIn(@Param("ids") Collection<Long> ids);

    @Query("SELECT c FROM categories c JOIN c.comics comic WHERE comic.id = :id")
    public List<Category> findByComicId(@Param("id") Long id);

    @Query(value = "SELECT * FROM categories c WHERE MATCH(c.name) AGAINST (?1 IN BOOLEAN MODE) LIMIT ?2",
            nativeQuery = true)
    public List<Category> searchByKeyword(String keyword, Integer limit);

}
