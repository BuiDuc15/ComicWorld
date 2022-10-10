package com.comicworld.layer.domain.entity;

import com.comicworld.layer.domain.entity.account.AdminAccount;
import com.comicworld.layer.domain.entity.account.ClientAccount;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "authorities")
public class Authority extends Base {

    @Column(name = "role")
    private String role;

    @ManyToMany
    @JoinTable(name = "admin_accounts_authorities",
               joinColumns = @JoinColumn(name = "authority_id"),
               inverseJoinColumns = @JoinColumn(name = "admin_account_id"))
    private Set<AdminAccount> adminAccounts = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "client_accounts_authorities",
               joinColumns = @JoinColumn(name = "authority_id"),
               inverseJoinColumns = @JoinColumn(name = "client_account_id"))
    private Set<ClientAccount> clientAccounts = new LinkedHashSet<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<AdminAccount> getAdminAccounts() {
        return adminAccounts;
    }

    public void setAdminAccounts(Set<AdminAccount> adminAccounts) {
        this.adminAccounts = adminAccounts;
    }

    public Set<ClientAccount> getClientAccounts() {
        return clientAccounts;
    }

    public void setClientAccounts(Set<ClientAccount> clientAccounts) {
        this.clientAccounts = clientAccounts;
    }
}
