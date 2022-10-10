package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.AuthTokenPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthTokenPairDAO extends JpaRepository<AuthTokenPair, Long> {

    @Query("SELECT p FROM auth_token_pairs p WHERE p.refreshToken = :refreshToken")
    public Optional<AuthTokenPair> findByRefreshToken(@Param("refreshToken") String refreshToken);

    @Query("SELECT p FROM auth_token_pairs p WHERE p.accessToken = :accessToken")
    public Optional<AuthTokenPair> findByAccessToken(@Param("accessToken") String accessToken);

}
