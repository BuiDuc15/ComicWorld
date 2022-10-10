package com.comicworld.layer.domain.dto.profile;

import com.comicworld.layer.domain.dto.base.BaseInDTO;

public class ProfileInDTO extends BaseInDTO {

    private String fullName;

    private String avatarLink;

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
