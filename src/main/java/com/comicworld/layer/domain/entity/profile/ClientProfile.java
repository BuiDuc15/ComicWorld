package com.comicworld.layer.domain.entity.profile;

import com.comicworld.layer.domain.entity.account.ClientAccount;

import javax.persistence.*;

@Entity(name = "client_profiles")
public class ClientProfile extends Profile {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "dob")
    private String dob;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "about")
    private String about;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private ClientAccount account;

    public ClientAccount getAccount() {
        return account;
    }

    public void setAccount(ClientAccount account) {
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
