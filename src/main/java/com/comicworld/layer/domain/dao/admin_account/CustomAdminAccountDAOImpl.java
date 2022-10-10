package com.comicworld.layer.domain.dao.admin_account;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class CustomAdminAccountDAOImpl implements CustomAdminAccountDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createFullTextIndexByColumn(String column) {
        Query nativeQuery = this.entityManager.createNativeQuery("ALTER TABLE admin_accounts ADD FULLTEXT(" + column + ")");
        nativeQuery.executeUpdate();
    }


}
