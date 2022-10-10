package com.comicworld.layer.domain.dto.profile;

import com.comicworld.layer.domain.dto.account.ClientAccountInDTO;
import com.comicworld.layer.domain.entity.profile.ClientProfile;

public class ClientProfileInDTO extends ProfileInDTO {

    private String phoneNumber;

    private String dob;

    private Integer gender;

    private String about;

    private ClientAccountInDTO account;

    public ClientProfile toClientProfile() {
        ClientProfile clientProfile = new ClientProfile();

        clientProfile.setId(getId());
        clientProfile.setAbout(getAbout());
        clientProfile.setDob(getDob());
        clientProfile.setGender(getGender());
        clientProfile.setPhoneNumber(getPhoneNumber());
        clientProfile.setFullName(getFullName());
        clientProfile.setUsername(getUsername());
        clientProfile.setAvatarLink(getAvatarLink());

        clientProfile.setAccount(getAccount() != null ? getAccount().toClientAccount() : null);

        return clientProfile;
    }

    public ClientAccountInDTO getAccount() {
        return account;
    }

    public void setAccount(ClientAccountInDTO account) {
        this.account = account;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
