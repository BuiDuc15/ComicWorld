package com.comicworld.layer.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "black_list_auth_tokens")
public class BlackListAuthToken extends Base {

    @Column(name = "code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
