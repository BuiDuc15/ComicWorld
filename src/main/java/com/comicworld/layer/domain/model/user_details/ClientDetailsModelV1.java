package com.comicworld.layer.domain.model.user_details;

import com.comicworld.layer.domain.entity.Authority;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientDetailsModelV1 implements UserDetails {

    private ClientAccount account;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    public ClientDetailsModelV1() {
        super();
    }

    public ClientDetailsModelV1(ClientAccount account) {
        super();
        this.account = account;
        this.initAuthorities(account.getAuthorities());
    }

    private void initAuthorities(Collection<Authority> accountAuthorities) {
        accountAuthorities.forEach(authority -> this.authorities.add(new SimpleGrantedAuthority(authority.getRole())));
    }

    public ClientAccount getAccount() {
        return account;
    }

    public void setAccount(ClientAccount account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return account.getEnabled();
    }
}
