package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPDAO extends JpaRepository<OTP, Long> {

    @Query("SELECT o FROM otps o LEFT JOIN FETCH o.account WHERE o.code = :code")
    public Optional<OTP> findByCodeWithAllRelationshipsLoadedEagerly(@Param("code") String code);

}
