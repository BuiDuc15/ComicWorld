package com.comicworld.layer.domain.entity.translator_group;

import com.comicworld.layer.domain.entity.Base;
import com.comicworld.layer.domain.entity.account.AdminAccount;

import javax.persistence.*;

@Entity(name = "translator_group_identities")
public class TranslatorGroupIdentity extends Base {

    @Column(name = "fake_id")
    private String fakeId;

    @Column(name = "role")
    private String role;

    @Column(name = "role_in_number")
    private Integer roleInNumber;

    @Column(name = "joined_at")
    private Long joinedAt;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private String dob;

    @Column(name = "avatar_link")
    private String avatarLink;

    @Column(name = "contact_link")
    private String contactLink;

    @Column(name = "nick_name")
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AdminAccount account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "translator_group_id")
    private TranslatorGroup translatorGroup;

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

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }

    public AdminAccount getAccount() {
        return account;
    }

    public void setAccount(AdminAccount account) {
        this.account = account;
    }

    public TranslatorGroup getTranslatorGroup() {
        return translatorGroup;
    }

    public void setTranslatorGroup(TranslatorGroup translatorGroup) {
        this.translatorGroup = translatorGroup;
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
