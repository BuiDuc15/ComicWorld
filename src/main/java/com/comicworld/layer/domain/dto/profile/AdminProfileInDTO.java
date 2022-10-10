package com.comicworld.layer.domain.dto.profile;

import com.comicworld.layer.domain.dto.account.AdminAccountInDTO;
import com.comicworld.layer.domain.entity.profile.AdminProfile;

public class AdminProfileInDTO extends ProfileInDTO {

    private AdminAccountInDTO account;

    public AdminProfile toAdminProfile() {
        AdminProfile profile = new AdminProfile();

        profile.setAvatarLink(getAvatarLink());
        profile.setId(getId());
        profile.setFullName(getFullName());
        profile.setUsername(getUsername());

        profile.setAccount(getAccount() != null ? getAccount().toAdminAccount() : null);

        return profile;
    }

    public AdminAccountInDTO getAccount() {
        return account;
    }

    public void setAccount(AdminAccountInDTO account) {
        this.account = account;
    }
}
