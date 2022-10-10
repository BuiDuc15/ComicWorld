package com.comicworld.layer.domain.dto.account;

import com.comicworld.layer.domain.dto.base.BaseInDTO;

public class AccountInDTO extends BaseInDTO {

    private String username;

    private String password;

    private Integer accountType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}
