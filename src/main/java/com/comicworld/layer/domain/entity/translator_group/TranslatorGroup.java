package com.comicworld.layer.domain.entity.translator_group;

import com.comicworld.layer.domain.entity.Base;
import com.comicworld.layer.domain.entity.Comic;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "translator_groups")
public class TranslatorGroup extends Base {

    @Column(name = "name")
    private String name;

    @Column(name = "fake_id")
    private String fakeId;

    @Column(name = "description", columnDefinition = "VARCHAR(3000)")
    private String description;

    @Column(name = "avatar_link")
    private String avatarLink;

    @Column(name = "shout_out")
    private Long shoutOuts;

    @Column(name = "complaint")
    private Long complaints;

    @OneToMany(mappedBy = "translatorGroup")
    private Set<TranslatorGroupIdentity> translatorGroupIdentities = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "comics_translator_groups",
               joinColumns = @JoinColumn(name = "translator_group_id"),
               inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics = new LinkedHashSet<>();

    public Set<Comic> getComics() {
        return comics;
    }

    public void setComics(Set<Comic> comics) {
        this.comics = comics;
    }

    public Set<TranslatorGroupIdentity> getTranslatorGroupIdentities() {
        return translatorGroupIdentities;
    }

    public void setTranslatorGroupIdentities(Set<TranslatorGroupIdentity> translatorGroupIdentities) {
        this.translatorGroupIdentities = translatorGroupIdentities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public Long getShoutOuts() {
        return shoutOuts;
    }

    public void setShoutOuts(Long shoutOuts) {
        this.shoutOuts = shoutOuts;
    }

    public Long getComplaints() {
        return complaints;
    }

    public void setComplaints(Long complaints) {
        this.complaints = complaints;
    }
}
