package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Optional;

@Repository
public interface AuthorityDAO extends JpaRepository<Authority, Long> {

    @Query("SELECT a FROM authorities a JOIN a.adminAccounts aa WHERE aa.id = :id")
    public LinkedHashSet<Authority> findByAdminAccountId(@Param("id") Long id);

    @Query("SELECT a FROM authorities a JOIN a.clientAccounts ca WHERE ca.id = :id")
    public LinkedHashSet<Authority> findByClientAccountId(@Param("id") Long id);

    @Query("SELECT a FROM authorities a LEFT JOIN FETCH a.adminAccounts WHERE a.id = :id")
    public Optional<Authority> findByIdWithAdminAccountsLoadedEagerly(@Param("id") Long id);

    @Query("SELECT a FROM authorities a LEFT JOIN FETCH a.adminAccounts WHERE a.role = :role")
    public Optional<Authority> findByRoleWithAdminAccountsLoadedEagerly(@Param("role") String role);

    @Query("SELECT a FROM authorities a LEFT JOIN FETCH a.clientAccounts WHERE a.role = :role")
    public Optional<Authority> findByRoleWithClientAccountsLoadedEagerly(@Param("role") String role);

}
