package com.comicworld.layer.domain.dto.authority;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.entity.Authority;

public class AuthorityOutDTO extends BaseOutDTO {

    private String role;

    public AuthorityOutDTO(Authority authority) {
        this.setId(authority.getId());
        this.setRole(authority.getRole());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
