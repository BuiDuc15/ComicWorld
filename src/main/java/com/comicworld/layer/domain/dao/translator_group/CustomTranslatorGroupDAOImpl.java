package com.comicworld.layer.domain.dao.translator_group;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class CustomTranslatorGroupDAOImpl implements CustomTranslatorGroupDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createFullTextIndexByColumn(String column) {
        Query nativeQuery = this.entityManager.createNativeQuery("ALTER TABLE translator_groups ADD FULLTEXT(" + column + ")");
        nativeQuery.executeUpdate();
    }

}
