package com.comicworld.layer.domain.dao;

import com.comicworld.layer.domain.entity.BlackListAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListAuthTokenDAO extends JpaRepository<BlackListAuthToken, Long> {

    public Boolean existsByCode(String code);

}
