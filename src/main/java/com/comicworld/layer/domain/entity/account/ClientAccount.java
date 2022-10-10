package com.comicworld.layer.domain.entity.account;

import com.comicworld.layer.domain.entity.Authority;
import com.comicworld.layer.domain.entity.OTP;
import com.comicworld.layer.domain.entity.profile.ClientProfile;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "client_accounts")
public class ClientAccount extends Account {

    @ManyToMany(mappedBy = "clientAccounts")
    private Set<Authority> authorities = new LinkedHashSet<>();

    @OneToOne(mappedBy = "account")
    private ClientProfile clientProfile;

    @OneToOne(mappedBy = "account")
    private OTP otp;

    public OTP getOtp() {
        return otp;
    }

    public void setOtp(OTP otp) {
        this.otp = otp;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public ClientProfile getClientProfile() {
        return clientProfile;
    }

    public void setClientProfile(ClientProfile clientProfile) {
        this.clientProfile = clientProfile;
    }
}
