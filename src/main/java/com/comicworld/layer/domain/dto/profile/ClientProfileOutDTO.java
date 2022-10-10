package com.comicworld.layer.domain.dto.profile;

import com.comicworld.layer.domain.dto.account.ClientAccountOutDTO;
import com.comicworld.layer.domain.entity.profile.ClientProfile;
import org.hibernate.LazyInitializationException;

public class ClientProfileOutDTO extends ProfileOutDTO {

    private String phoneNumber;

    private String dob;

    private Integer gender;

    private String about;
//
//    private ClientAccountOutDTO account;

    public ClientProfileOutDTO(ClientProfile clientProfile) {
        this.setId(clientProfile.getId());
        this.setAbout(clientProfile.getAbout());
        this.setDob(clientProfile.getDob());
        this.setGender(clientProfile.getGender());
        this.setPhoneNumber(clientProfile.getPhoneNumber());
        this.setAvatarLink(clientProfile.getAvatarLink());
        this.setFullName(clientProfile.getFullName());
        this.setUsername(clientProfile.getUsername());

//        try {
//            if(clientProfile.getAccount() != null)
//                this.setAccount(new ClientAccountOutDTO(clientProfile.getAccount()));
//        }
//        catch (LazyInitializationException e) {
//            System.out.println("CLIENT ACCOUNT LAZY LOAD FROM CLIENT PROFILE OUT DTO");
//        }

    }

//    public ClientAccountOutDTO getAccount() {
//        return account;
//    }
//
//    public void setAccount(ClientAccountOutDTO account) {
//        this.account = account;
//    }

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
