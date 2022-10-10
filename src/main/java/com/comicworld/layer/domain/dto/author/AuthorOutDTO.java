package com.comicworld.layer.domain.dto.author;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.Author;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorOutDTO extends BaseOutDTO {

    private String name;

    private String dob;

    private String avatarLink;

    private String fakeId;

    private Set<ComicOutDTO> comics = new LinkedHashSet<>();

    public AuthorOutDTO(Author author) {
        this.setId(author.getId());
        this.setName(author.getName());
        this.setDob(author.getDob());
        this.setAvatarLink(author.getAvatarLink());
        this.setFakeId(author.getFakeId());

        try {
            if(author.getComics() != null)
                this.setComics(author.getComics().stream()
                        .map(comic -> new ComicOutDTO(comic))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("COMICS LAZY LOAD FROM AUTHOR OUT DTO");
        }
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }

}
