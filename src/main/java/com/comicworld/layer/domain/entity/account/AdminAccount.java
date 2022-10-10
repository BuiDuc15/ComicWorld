package com.comicworld.layer.domain.entity.account;

import com.comicworld.layer.domain.entity.Authority;
import com.comicworld.layer.domain.entity.profile.AdminProfile;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "admin_accounts")
public class AdminAccount extends Account {

    @ManyToMany(mappedBy = "adminAccounts")
    private Set<Authority> authorities = new LinkedHashSet<>();

    @OneToOne(mappedBy = "account")
    private AdminProfile profile;

    @OneToMany(mappedBy = "account")
    private Set<TranslatorGroupIdentity> translatorGroupIdentities = new LinkedHashSet<>();

    public Set<TranslatorGroupIdentity> getTranslatorGroupIdentities() {
        return translatorGroupIdentities;
    }

    public void setTranslatorGroupIdentities(Set<TranslatorGroupIdentity> translatorGroupIdentities) {
        this.translatorGroupIdentities = translatorGroupIdentities;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public AdminProfile getProfile() {
        return profile;
    }

    public void setProfile(AdminProfile profile) {
        this.profile = profile;
    }
}
