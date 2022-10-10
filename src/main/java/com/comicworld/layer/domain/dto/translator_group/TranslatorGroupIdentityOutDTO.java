package com.comicworld.layer.domain.dto.translator_group;

import com.comicworld.layer.domain.dto.account.AdminAccountOutDTO;
import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;
import org.hibernate.LazyInitializationException;

public class TranslatorGroupIdentityOutDTO extends BaseOutDTO {

    private String fakeId;

    private String role;

    private Integer roleInNumber;

    private Long joinedAt;

    private Integer gender;

    private String phoneNumber;

    private String email;

    private String dob;

    private String contactLink;

    private String avatarLink;

    private String nickname;

    private AdminAccountOutDTO account;

    private TranslatorGroupOutDTO translatorGroup;

    public TranslatorGroupIdentityOutDTO(TranslatorGroupIdentity translatorGroupIdentity) {
        this.setId(translatorGroupIdentity.getId());
        this.setFakeId(translatorGroupIdentity.getFakeId());
        this.setRole(translatorGroupIdentity.getRole());
        this.setJoinedAt(translatorGroupIdentity.getJoinedAt());
        this.setGender(translatorGroupIdentity.getGender());
        this.setPhoneNumber(translatorGroupIdentity.getPhoneNumber());
        this.setEmail(translatorGroupIdentity.getEmail());
        this.setDob(translatorGroupIdentity.getDob());
        this.setContactLink(translatorGroupIdentity.getContactLink());
        this.setRoleInNumber(translatorGroupIdentity.getRoleInNumber());
        this.setAvatarLink(translatorGroupIdentity.getAvatarLink());
        this.setNickname(translatorGroupIdentity.getNickname());

        try {
            if(translatorGroupIdentity.getAccount() != null)
                this.setAccount(new AdminAccountOutDTO(translatorGroupIdentity.getAccount()));
        }
        catch (LazyInitializationException e) {
            System.out.println("ADMIN ACCOUNT LAZY LOAD FROM TRANSLATOR GROUP IDENTITY OUT DTO");
        }

        try {
            if(translatorGroupIdentity.getTranslatorGroup() != null)
                this.setTranslatorGroup(new TranslatorGroupOutDTO(translatorGroupIdentity.getTranslatorGroup()));
        }
        catch (LazyInitializationException e) {
            System.out.println("TRANSLATOR GROUP LAZY LOAD FROM TRANSLATOR GROUP IDENTITY OUT DTO");
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public Integer getRoleInNumber() {
        return roleInNumber;
    }

    public void setRoleInNumber(Integer roleInNumber) {
        this.roleInNumber = roleInNumber;
    }

    public AdminAccountOutDTO getAccount() {
        return account;
    }

    public void setAccount(AdminAccountOutDTO account) {
        this.account = account;
    }

    public TranslatorGroupOutDTO getTranslatorGroup() {
        return translatorGroup;
    }

    public void setTranslatorGroup(TranslatorGroupOutDTO translatorGroup) {
        this.translatorGroup = translatorGroup;
    }

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Long joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getContactLink() {
        return contactLink;
    }

    public void setContactLink(String contactLink) {
        this.contactLink = contactLink;
    }

}
