package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.profile.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientProfileDAO extends JpaRepository<ClientProfile, Long> {

    @Query("SELECT cp FROM client_profiles cp JOIN cp.account a WHERE a.id = :id")
    public Optional<ClientProfile> findByAccountId(@Param("id") Long id);

}
