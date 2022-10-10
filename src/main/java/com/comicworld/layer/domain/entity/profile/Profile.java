package com.comicworld.layer.domain.entity.profile;

import com.comicworld.layer.domain.entity.Base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Profile extends Base {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "avatar_link")
    private String avatarLink;

    @Column(name = "username")
    private String username;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
