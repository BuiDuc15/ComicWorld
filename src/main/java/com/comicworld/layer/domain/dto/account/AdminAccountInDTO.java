package com.comicworld.layer.domain.dto.account;

import com.comicworld.layer.domain.dto.profile.AdminProfileInDTO;
import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityInDTO;
import com.comicworld.layer.domain.entity.account.AdminAccount;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminAccountInDTO extends AccountInDTO {

    private AdminProfileInDTO profile;

    private Set<TranslatorGroupIdentityInDTO> translatorGroupIdentities;

    public AdminAccount toAdminAccount() {
        AdminAccount account = new AdminAccount();

        account.setId(this.getId());
        account.setUsername(this.getUsername());
        account.setPassword(this.getPassword());
        account.setAccountType(this.getAccountType());

        account.setProfile(getProfile() != null ? getProfile().toAdminProfile() : null);

        account.setTranslatorGroupIdentities(getTranslatorGroupIdentities() != null ?
                getTranslatorGroupIdentities().stream()
                        .map(translatorGroupIdentity -> translatorGroupIdentity.toTranslatorGroupIdentity())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        return account;
    }

    public AdminProfileInDTO getProfile() {
        return profile;
    }

    public void setProfile(AdminProfileInDTO profile) {
        this.profile = profile;
    }

    public Set<TranslatorGroupIdentityInDTO> getTranslatorGroupIdentities() {
        return translatorGroupIdentities;
    }

    public void setTranslatorGroupIdentities(Set<TranslatorGroupIdentityInDTO> translatorGroupIdentities) {
        this.translatorGroupIdentities = translatorGroupIdentities;
    }
}
