package com.comicworld.layer.domain.dto.account;

import com.comicworld.layer.domain.dto.authority.AuthorityOutDTO;
import com.comicworld.layer.domain.dto.profile.AdminProfileOutDTO;
import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityOutDTO;
import com.comicworld.layer.domain.entity.account.AdminAccount;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminAccountOutDTO extends AccountOutDTO {

    private Set<AuthorityOutDTO> authorities = new LinkedHashSet<>();

    private AdminProfileOutDTO profile;

    private Set<TranslatorGroupIdentityOutDTO> translatorGroupIdentities = new LinkedHashSet<>();

    private String username;

    public AdminAccountOutDTO(AdminAccount adminAccount) {
        this.setId(adminAccount.getId());
        this.setAccountType(adminAccount.getAccountType());
        this.setUsername(adminAccount.getUsername());

        try {
            if(adminAccount.getAuthorities() != null)
                this.setAuthorities(adminAccount.getAuthorities().stream()
                        .map(authority -> new AuthorityOutDTO(authority))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("AUTHORITIES LAZY LOAD FROM ADMIN ACCOUNT OUT DTO");
        }

        try {
            if(adminAccount.getProfile() != null)
                this.setProfile(new AdminProfileOutDTO(adminAccount.getProfile()));
        }
        catch (LazyInitializationException e) {
            System.out.println("PROFILE LAZY LOAD FROM ADMIN ACCOUNT OUT DTO");
        }

        try {
            if(adminAccount.getTranslatorGroupIdentities() != null)
                this.setTranslatorGroupIdentities(adminAccount.getTranslatorGroupIdentities().stream()
                        .map(translatorGroupIdentity -> new TranslatorGroupIdentityOutDTO(translatorGroupIdentity))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("TRANSLATOR GROUP IDENTITIES LAZY LOAD FROM ADMIN ACCOUNT OUT DTO");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<AuthorityOutDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityOutDTO> authorities) {
        this.authorities = authorities;
    }

    public AdminProfileOutDTO getProfile() {
        return profile;
    }

    public void setProfile(AdminProfileOutDTO profile) {
        this.profile = profile;
    }

    public Set<TranslatorGroupIdentityOutDTO> getTranslatorGroupIdentities() {
        return translatorGroupIdentities;
    }

    public void setTranslatorGroupIdentities(Set<TranslatorGroupIdentityOutDTO> translatorGroupIdentities) {
        this.translatorGroupIdentities = translatorGroupIdentities;
    }
}
