package com.comicworld.layer.domain.model.user_details;

import com.comicworld.layer.domain.entity.Authority;
import com.comicworld.layer.domain.entity.account.AdminAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminDetailsModelV1 implements UserDetails {

    private AdminAccount account;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    public AdminDetailsModelV1() {
        super();
    }

    public AdminDetailsModelV1(AdminAccount account) {
        super();
        this.account = account;
        this.initAuthorities(account.getAuthorities());
    }

    private void initAuthorities(Collection<Authority> accountAuthorities) {
        accountAuthorities.forEach(authority -> this.authorities.add(new SimpleGrantedAuthority(authority.getRole())));
    }

    public AdminAccount getAccount() {
        return account;
    }

    public void setAccount(AdminAccount account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.account.getPassword();
    }

    @Override
    public String getUsername() {
        return this.account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.account.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.account.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.account.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.account.getEnabled();
    }
}
