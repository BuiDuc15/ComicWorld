package com.comicworld.layer.domain.dao.category;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class CustomCategoryDAOImpl implements CustomCategoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createFullTextIndexByColumn(String column) {
        Query nativeQuery = this.entityManager.createNativeQuery("ALTER TABLE categories ADD FULLTEXT(" + column + ")");
        nativeQuery.executeUpdate();
    }
}
