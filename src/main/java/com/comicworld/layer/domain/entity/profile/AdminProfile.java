package com.comicworld.layer.domain.entity.profile;

import com.comicworld.layer.domain.entity.account.AdminAccount;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "admin_profiles")
public class AdminProfile extends Profile {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AdminAccount account;

    public AdminAccount getAccount() {
        return account;
    }

    public void setAccount(AdminAccount account) {
        this.account = account;
    }
}
