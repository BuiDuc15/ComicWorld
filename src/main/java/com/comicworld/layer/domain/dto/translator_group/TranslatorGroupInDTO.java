package com.comicworld.layer.domain.dto.translator_group;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TranslatorGroupInDTO extends BaseInDTO {

    private String name;

    private String fakeId;

    private String description;

    private String avatarLink;

    private Long shoutOuts;

    private Long complaints;

    private Set<TranslatorGroupIdentityInDTO> translatorGroupIdentities;

    private Set<ComicInDTO> comics;

    public TranslatorGroup toTranslatorGroup() {
        TranslatorGroup translatorGroup = new TranslatorGroup();

        translatorGroup.setId(getId());
        translatorGroup.setName(getName());
        translatorGroup.setFakeId(getFakeId());
        translatorGroup.setDescription(getDescription());
        translatorGroup.setAvatarLink(getAvatarLink());
        translatorGroup.setShoutOuts(getShoutOuts());
        translatorGroup.setComplaints(getComplaints());

        translatorGroup.setTranslatorGroupIdentities(getTranslatorGroupIdentities() != null ?
                getTranslatorGroupIdentities().stream()
                        .map(translatorGroupIdentity -> translatorGroupIdentity.toTranslatorGroupIdentity())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        translatorGroup.setComics(getComics() != null ?
                getComics().stream()
                        .map(comic -> comic.toComic())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        return translatorGroup;
    }

    public Set<TranslatorGroupIdentityInDTO> getTranslatorGroupIdentities() {
        return translatorGroupIdentities;
    }

    public void setTranslatorGroupIdentities(Set<TranslatorGroupIdentityInDTO> translatorGroupIdentities) {
        this.translatorGroupIdentities = translatorGroupIdentities;
    }

    public Set<ComicInDTO> getComics() {
        return comics;
    }

    public void setComics(Set<ComicInDTO> comics) {
        this.comics = comics;
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
