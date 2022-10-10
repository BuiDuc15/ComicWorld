package com.comicworld.layer.domain.dao.comic;

import com.comicworld.layer.domain.entity.Category;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.model.PageModel;
import com.comicworld.utils.Env;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CustomComicDAOImpl implements CustomComicDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageModel findByCategoryFakeIdWithChaptersLoadedEagerly(Map<String, Object> params) {
        String fakeId = (String) params.get("fakeId");

        Integer limit = Env.DEFAULT_PAGE_SIZE;

        Integer page = (Integer) params.get("page");

        Integer offset = page * Env.DEFAULT_PAGE_SIZE;

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Comic> fetchQuery = builder.createQuery(Comic.class);

        Root<Comic> fetchQueryRoot = fetchQuery.from(Comic.class);

        fetchQueryRoot.fetch("chapters", JoinType.LEFT);

        SetJoin<Comic, Category> fetchQueryCategories = fetchQueryRoot.joinSet("categories", JoinType.INNER);

        fetchQuery.select(fetchQueryRoot);

        fetchQuery.where(builder.equal(fetchQueryCategories.get("fakeId"), fakeId));

        fetchQuery.orderBy(this.getOrders(params, builder, fetchQueryRoot));

        /////////////////////////////////////////////////////////////////////////////////////////

        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);

        Root<Comic> countQueryRoot = countQuery.from(Comic.class);

        SetJoin<Comic, Category> countQueryCategories = countQueryRoot.joinSet("categories", JoinType.INNER);

        countQuery.select(builder.count(countQueryRoot));

        countQuery.where(builder.equal(countQueryCategories.get("fakeId"), fakeId));

        List<Comic> content = this.entityManager.createQuery(fetchQuery)
                                                .setFirstResult(offset)
                                                .setMaxResults(limit)
                                                .getResultList();

        Long totalElements = this.entityManager.createQuery(countQuery)
                                                .getSingleResult();

        Long totalPages = totalElements % Env.DEFAULT_PAGE_SIZE == 0 ?
                                    totalElements / Env.DEFAULT_PAGE_SIZE : totalElements / Env.DEFAULT_PAGE_SIZE + 1;

        return new PageModel(page, totalPages, content);
    }

    private List<Order> getOrders(Map<String, Object> params,
                                  CriteriaBuilder builder,
                                  Root root) {
        List<Order> orders = new ArrayList<>();

        if(params.containsKey("view")) {
            String viewSortOption = (String) params.get("view");

            if(viewSortOption.equalsIgnoreCase("ASC")) orders.add(builder.asc(root.get("view")));
            else orders.add(builder.desc(root.get("view")));
        }

        if(params.containsKey("name")) {
            String nameSortOption = (String) params.get("name");

            if(nameSortOption.equalsIgnoreCase("ASC")) orders.add(builder.asc(root.get("displayedName")));
            else orders.add(builder.desc(root.get("displayedName")));
        }

        if(params.containsKey("lastUpdate")) {
            String lastUpdateSortOption = (String) params.get("lastUpdate");

            if(lastUpdateSortOption.equalsIgnoreCase("ASC")) orders.add(builder.asc(root.get("lastUpdatedAt")));
            else orders.add(builder.desc(root.get("lastUpdatedAt")));
        }

        return orders;
    }

    @Override
    public void updateLoveOfComicById(Long id, String action) throws NoResultException {
        TypedQuery<Comic> typedQuery = this.entityManager.createQuery("SELECT c FROM comics c WHERE c.id = :id",
                                                                        Comic.class);
        typedQuery.setParameter("id", id);
        typedQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        Comic comic = typedQuery.getSingleResult();

        if(action.equalsIgnoreCase("ADD")) comic.setLove(comic.getLove() + 1);
        else comic.setLove(comic.getLove() - 1);
    }

    @Override
    public void updateDislikeOfComicById(Long id, String action) throws NoResultException {
        TypedQuery<Comic> typedQuery = this.entityManager.createQuery("SELECT c FROM comics c WHERE c.id = :id",
                                                                        Comic.class);
        typedQuery.setParameter("id", id);
        typedQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        Comic comic = typedQuery.getSingleResult();

        if(action.equalsIgnoreCase("ADD")) comic.setDislike(comic.getDislike() + 1);
        else comic.setDislike(comic.getDislike() - 1);
    }

    @Override
    public void updateViewOfComicById(Long id) throws OptimisticLockException, NoResultException {
        TypedQuery<Comic> typedQuery = this.entityManager.createQuery("SELECT c FROM comics c WHERE c.id = :id",
                                                                        Comic.class);
        typedQuery.setParameter("id", id);
        typedQuery.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        Comic comic = typedQuery.getSingleResult();

        comic.setView(comic.getView() + 1);
    }

    @Override
    public List<Comic> findTop10MostViewedComicsWithChaptersLoadedEagerly() {
        TypedQuery<Comic> typedQuery = this.entityManager.createQuery("SELECT c FROM comics c LEFT JOIN FETCH c.chapters ORDER BY c.view DESC",
                Comic.class);

        typedQuery.setMaxResults(10);

        return typedQuery.getResultList();
    }

    @Override
    public List<Comic> findTop10MostPopularComicsWithChaptersLoadedEagerly() {
        TypedQuery<Comic> typedQuery = this.entityManager.createQuery("SELECT c FROM comics c LEFT JOIN FETCH c.chapters ORDER BY c.love DESC, c.view DESC",
                Comic.class);

        typedQuery.setMaxResults(10);

        return typedQuery.getResultList();
    }

    @Override
    public List<Comic> findTop15LastUpdatedComicsWithChaptersLoadedEagerly() {
        TypedQuery<Comic> typedQuery = this.entityManager.createQuery("SELECT c FROM comics c LEFT JOIN FETCH c.chapters ORDER BY c.lastUpdatedAt DESC",
                Comic.class);

        typedQuery.setMaxResults(15);

        return typedQuery.getResultList();
    }

    @Override
    public List<Comic> findTop10LatestComicsWithChaptersLoadedEagerly() {
        TypedQuery<Comic> typedQuery = this.entityManager.createQuery("SELECT c FROM comics c LEFT JOIN FETCH c.chapters ORDER BY c.createdAt DESC",
                Comic.class);

        typedQuery.setMaxResults(10);

        return typedQuery.getResultList();
    }


}















































