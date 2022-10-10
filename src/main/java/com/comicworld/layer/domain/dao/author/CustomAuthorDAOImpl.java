package com.comicworld.layer.domain.dao.author;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class CustomAuthorDAOImpl implements CustomAuthorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createFullTextIndexByColumn(String column) {
        Query nativeQuery = this.entityManager.createNativeQuery("ALTER TABLE authors ADD FULLTEXT(" + column + ")");
        nativeQuery.executeUpdate();
    }
}
