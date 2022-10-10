package com.comicworld.layer.domain.dao.admin_account;

import com.comicworld.layer.domain.entity.account.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminAccountDAO extends JpaRepository<AdminAccount, Long>, CustomAdminAccountDAO {

    @Query("SELECT ac FROM admin_accounts ac LEFT JOIN FETCH ac.authorities WHERE ac.username = :username")
    public Optional<AdminAccount> findByUsernameWithAuthoritiesLoadedEagerly(@Param("username") String username);

    @Query("SELECT ac FROM admin_accounts ac LEFT JOIN FETCH ac.authorities WHERE ac.id = :id")
    public Optional<AdminAccount> findByIdWithAuthoritiesLoadedEagerly(@Param("id") Long id);

    @Query("SELECT aa FROM admin_accounts aa WHERE aa.id = :id")
    public Optional<AdminAccount> findByIdWithAllRelationshipsLoadedLazily(@Param("id") Long id);

    @Query(value = "SELECT * FROM admin_accounts aa WHERE MATCH(aa.username) AGAINST (?1 IN BOOLEAN MODE) LIMIT ?2",
            nativeQuery = true)
    public List<AdminAccount> searchByKeyword(String keyword, Integer limit);

    @Query("SELECT COUNT(a.id) > 0 FROM admin_accounts a WHERE a.id = :accountId AND a.id IN (SELECT a2.id FROM comics c JOIN c.translatorGroups tg JOIN tg.translatorGroupIdentities tgi JOIN tgi.account a2 WHERE c.id = :comicId)")
    public Boolean isRelated(@Param("accountId") Long accountId, @Param("comicId") Long comicId);


}
