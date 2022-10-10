package com.comicworld.layer.domain.dao.alternative_name;

import com.comicworld.layer.domain.entity.AlternativeName;
import com.comicworld.utils.Env;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CustomAlternativeNameDAOImpl implements CustomAlternativeNameDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createFullTextIndexByColumn(String column) {
        Query nativeQuery = this.entityManager.createNativeQuery("ALTER TABLE alternative_names ADD FULLTEXT(" + column + ")");
        nativeQuery.executeUpdate();
    }

    @Override
    public List<AlternativeName> searchByName(Map<String, Object> params) {
        String name = (String) params.get("keyword");

        Integer page = (Integer) params.get("page");

        Integer offset = page * Env.DEFAULT_PAGE_SIZE;

        Integer limit = Env.DEFAULT_PAGE_SIZE;

        Query nativeQuery = this.entityManager.createNativeQuery("SELECT * FROM alternative_names an WHERE MATCH(an.name) AGAINST (?1 IN BOOLEAN MODE)",
                                                                AlternativeName.class);

        nativeQuery.setParameter(1, name);

        nativeQuery.setFirstResult(offset);
        nativeQuery.setMaxResults(limit);

        return nativeQuery.getResultList();
    }

    @Override
    public List<AlternativeName> searchFullByName(Map<String, Object> params) {
        Query nativeQuery = this.entityManager.createNativeQuery("SELECT * FROM alternative_names an" +
                        " WHERE MATCH(an.name) AGAINST (:name IN BOOLEAN MODE)",
                AlternativeName.class);

        String name = (String) params.get("keyword");

        nativeQuery.setParameter("name", name);

        return nativeQuery.getResultList();
    }
}






































