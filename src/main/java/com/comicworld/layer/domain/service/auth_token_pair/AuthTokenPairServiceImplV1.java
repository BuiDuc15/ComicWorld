package com.comicworld.layer.domain.service.auth_token_pair;


import com.comicworld.layer.domain.dao.AuthTokenPairDAO;
import com.comicworld.layer.domain.entity.AuthTokenPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("authTokenPairServiceImplV1")
@Transactional
public class AuthTokenPairServiceImplV1 implements AuthTokenPairService {

    @Autowired
    private AuthTokenPairDAO dao;

    @Override
    public AuthTokenPair saveOrUpdate(AuthTokenPair pair) {
        return this.dao.save(pair);
    }

    @Override
    public Optional<AuthTokenPair> findByRefreshToken(String refreshToken) {
        return this.dao.findByRefreshToken(refreshToken);
    }

    @Override
    public Optional<AuthTokenPair> findByAccessToken(String accessToken) {
        return this.dao.findByAccessToken(accessToken);
    }

}
