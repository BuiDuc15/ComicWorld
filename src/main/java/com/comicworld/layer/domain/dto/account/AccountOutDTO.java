package com.comicworld.layer.domain.dto.account;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;

public class AccountOutDTO extends BaseOutDTO {

    private Integer accountType;

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}
