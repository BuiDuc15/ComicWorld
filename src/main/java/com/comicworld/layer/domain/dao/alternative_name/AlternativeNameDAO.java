package com.comicworld.layer.domain.dao.alternative_name;

import com.comicworld.layer.domain.entity.AlternativeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AlternativeNameDAO extends JpaRepository<AlternativeName, Long>, CustomAlternativeNameDAO {

    @Query("SELECT an FROM alternative_names an LEFT JOIN FETCH an.comic c WHERE an.id IN :ids ORDER BY an.id ASC")
    public List<AlternativeName> findByIdIn(@Param("ids") Collection<Long> ids);

    @Query("SELECT an FROM alternative_names an JOIN an.comic c WHERE c.id = :id")
    public List<AlternativeName> findByComicId(@Param("id") Long id);

    @Query(value = "SELECT * FROM alternative_names an WHERE MATCH(an.name) AGAINST (?1 IN BOOLEAN MODE) LIMIT ?2",
            nativeQuery = true)
    public List<AlternativeName> searchByKeyword(String keyword, Integer limit);

}
