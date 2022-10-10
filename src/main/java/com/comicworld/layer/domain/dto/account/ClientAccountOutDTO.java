package com.comicworld.layer.domain.dto.account;

import com.comicworld.layer.domain.dto.authority.AuthorityOutDTO;
import com.comicworld.layer.domain.dto.profile.ClientProfileOutDTO;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientAccountOutDTO extends AccountOutDTO {

    private Set<AuthorityOutDTO> authorities = new LinkedHashSet<>();

    private ClientProfileOutDTO profile;

    public ClientAccountOutDTO(ClientAccount clientAccount) {
        this.setAccountType(clientAccount.getAccountType());
        this.setId(clientAccount.getId());

        try {
            if(clientAccount.getAuthorities() != null)
                this.setAuthorities(clientAccount.getAuthorities().stream()
                        .map(authority -> new AuthorityOutDTO(authority))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("AUTHORITES LAZY LOAD FROM CLIENT ACCOUNT OUT DTO");
        }

        try {
            if(clientAccount.getClientProfile() != null)
                this.setProfile(new ClientProfileOutDTO(clientAccount.getClientProfile()));
        }
        catch (LazyInitializationException e) {
            System.out.println("PROFILE LAZY LOAD FROM CLIENT ACCOUNT OUT DTO");
        }

    }

    public Set<AuthorityOutDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityOutDTO> authorities) {
        this.authorities = authorities;
    }

    public ClientProfileOutDTO getProfile() {
        return profile;
    }

    public void setProfile(ClientProfileOutDTO profile) {
        this.profile = profile;
    }
}
