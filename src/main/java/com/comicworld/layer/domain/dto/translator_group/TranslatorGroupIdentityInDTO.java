package com.comicworld.layer.domain.dto.translator_group;

import com.comicworld.layer.domain.dto.account.AdminAccountInDTO;
import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroupIdentity;

public class TranslatorGroupIdentityInDTO extends BaseInDTO {

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

    private AdminAccountInDTO account;

    private TranslatorGroupInDTO translatorGroup;

    public TranslatorGroupIdentity toTranslatorGroupIdentity() {
        TranslatorGroupIdentity translatorGroupIdentity = new TranslatorGroupIdentity();

        translatorGroupIdentity.setId(getId());
        translatorGroupIdentity.setFakeId(getFakeId());
        translatorGroupIdentity.setRole(getRole());
        translatorGroupIdentity.setJoinedAt(getJoinedAt());
        translatorGroupIdentity.setGender(getGender());
        translatorGroupIdentity.setPhoneNumber(getPhoneNumber());
        translatorGroupIdentity.setEmail(getEmail());
        translatorGroupIdentity.setDob(getDob());
        translatorGroupIdentity.setContactLink(getContactLink());
        translatorGroupIdentity.setRoleInNumber(getRoleInNumber());
        translatorGroupIdentity.setAvatarLink(getAvatarLink());
        translatorGroupIdentity.setNickname(getNickname());

        translatorGroupIdentity.setAccount(getAccount() != null ? getAccount().toAdminAccount() : null);

        translatorGroupIdentity.setTranslatorGroup(getTranslatorGroup() != null ? getTranslatorGroup().toTranslatorGroup() : null);

        return translatorGroupIdentity;
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

    public AdminAccountInDTO getAccount() {
        return account;
    }

    public void setAccount(AdminAccountInDTO account) {
        this.account = account;
    }

    public TranslatorGroupInDTO getTranslatorGroup() {
        return translatorGroup;
    }

    public void setTranslatorGroup(TranslatorGroupInDTO translatorGroup) {
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
