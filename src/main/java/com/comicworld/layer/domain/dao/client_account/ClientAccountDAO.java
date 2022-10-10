package com.comicworld.layer.domain.dao.client_account;

import com.comicworld.layer.domain.entity.account.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientAccountDAO extends JpaRepository<ClientAccount, Long> {

    @Query("SELECT ac FROM client_accounts ac LEFT JOIN FETCH ac.authorities WHERE ac.username = :username")
    public Optional<ClientAccount> findByUsernameWithAuthoritiesLoadedEagerly(@Param("username") String username);

    @Query("SELECT ac FROM client_accounts ac WHERE ac.username = :username")
    public Optional<ClientAccount> findByUsernameWithAuthoritiesLoadedLazily(@Param("username") String username);

    @Query("SELECT ac FROM client_accounts ac LEFT JOIN FETCH ac.authorities WHERE ac.id = :id")
    public Optional<ClientAccount> findByIdWithAuthoritiesLoadedEagerly(@Param("id") Long id);

    @Query("SELECT aa FROM client_accounts aa WHERE aa.id = :id")
    public Optional<ClientAccount> findByIdWithAllRelationshipsLoadedLazily(@Param("id") Long id);

    public Boolean existsByUsername(String username);

}
