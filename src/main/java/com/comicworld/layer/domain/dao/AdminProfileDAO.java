package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.profile.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminProfileDAO extends JpaRepository<AdminProfile, Long> {

    @Query("SELECT ap FROM admin_profiles ap JOIN ap.account a WHERE a.id = :id")
    public Optional<AdminProfile> findByAccountId(@Param("id") Long id);

}
