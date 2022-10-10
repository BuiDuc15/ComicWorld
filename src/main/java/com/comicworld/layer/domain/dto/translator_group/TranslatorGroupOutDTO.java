package com.comicworld.layer.domain.dto.translator_group;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TranslatorGroupOutDTO extends BaseOutDTO {

    private String name;

    private String fakeId;

    private String description;

    private String avatarLink;

    private Long shoutOuts;

    private Long complaints;

    private Set<TranslatorGroupIdentityOutDTO> translatorGroupIdentities;

    private Set<ComicOutDTO> comics;

    public TranslatorGroupOutDTO(TranslatorGroup translatorGroup) {
        this.setId(translatorGroup.getId());
        this.setName(translatorGroup.getName());
        this.setFakeId(translatorGroup.getFakeId());
        this.setDescription(translatorGroup.getDescription());
        this.setAvatarLink(translatorGroup.getAvatarLink());
        this.setShoutOuts(translatorGroup.getShoutOuts());
        this.setComplaints(translatorGroup.getComplaints());

        try {
            if(translatorGroup.getTranslatorGroupIdentities() != null)
                this.setTranslatorGroupIdentities(translatorGroup.getTranslatorGroupIdentities().stream()
                        .map(translatorGroupIdentity -> new TranslatorGroupIdentityOutDTO(translatorGroupIdentity))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("TRANSLATOR GROUP IDENTITIES LAZY LOAD FROM TRANSLATOR GROUP OUT DTO");
        }

        try {
            if(translatorGroup.getComics() != null)
                this.setComics(translatorGroup.getComics().stream()
                        .map(comic -> new ComicOutDTO(comic))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("COMICS LAZY LOAD FROM TRANSLATOR GROUP OUT DTO");
        }
    }

    public Set<TranslatorGroupIdentityOutDTO> getTranslatorGroupIdentities() {
        return translatorGroupIdentities;
    }

    public void setTranslatorGroupIdentities(Set<TranslatorGroupIdentityOutDTO> translatorGroupIdentities) {
        this.translatorGroupIdentities = translatorGroupIdentities;
    }

    public Set<ComicOutDTO> getComics() {
        return comics;
    }

    public void setComics(Set<ComicOutDTO> comics) {
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
