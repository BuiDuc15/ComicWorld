package com.comicworld.layer.domain.dto.profile;

import com.comicworld.layer.domain.dto.account.AdminAccountOutDTO;
import com.comicworld.layer.domain.entity.profile.AdminProfile;
import org.hibernate.LazyInitializationException;

public class AdminProfileOutDTO extends ProfileOutDTO {

    private AdminAccountOutDTO account;

    public AdminProfileOutDTO(AdminProfile adminProfile) {
        this.setId(adminProfile.getId());
        this.setAvatarLink(adminProfile.getAvatarLink());
        this.setFullName(adminProfile.getFullName());
        this.setUsername(adminProfile.getUsername());

        try {
            if(adminProfile.getAccount() != null)
                this.setAccount(new AdminAccountOutDTO(adminProfile.getAccount()));
        }
        catch (LazyInitializationException e) {
            System.out.println("ADMIN ACCOUNT LAZY LOAD FROM ADMIN PROFILE OUT DTO");
        }
    }

    public AdminAccountOutDTO getAccount() {
        return account;
    }

    public void setAccount(AdminAccountOutDTO account) {
        this.account = account;
    }
}
