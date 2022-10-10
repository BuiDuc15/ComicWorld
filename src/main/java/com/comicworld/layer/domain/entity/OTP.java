package com.comicworld.layer.domain.entity;

import com.comicworld.layer.domain.entity.account.ClientAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "otps")
public class OTP extends Base {

    @Column(name = "code")
    private String code;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "expires_at")
    private Long expiresAt;

    @Column(name = "is_valid")
    private Boolean isValid;

    @OneToOne
    @JoinColumn(name = "account_id")
    private ClientAccount account;

    public ClientAccount getAccount() {
        return account;
    }

    public void setAccount(ClientAccount account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
