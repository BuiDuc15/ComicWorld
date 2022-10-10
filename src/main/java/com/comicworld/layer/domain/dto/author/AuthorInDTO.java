package com.comicworld.layer.domain.dto.author;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.entity.Author;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorInDTO extends BaseInDTO {

    private String name;

    private String dob;

    private String avatarLink;

    private String fakeId;

    private Set<ComicInDTO> comics;

    public Author toAuthor() {
        Author author = new Author();

        author.setName(getName());
        author.setDob(getDob());
        author.setAvatarLink(getAvatarLink());
        author.setFakeId(getFakeId());
        author.setId(getId());

        author.setComics(getComics() != null ?
                getComics().stream()
                        .map(comic -> comic.toComic())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        return author;
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
