package com.comicworld.layer.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "auth_token_pairs")
public class AuthTokenPair extends Base {

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
