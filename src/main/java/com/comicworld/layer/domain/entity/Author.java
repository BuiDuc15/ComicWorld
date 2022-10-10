package com.comicworld.layer.domain.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "authors")
public class Author extends Base {

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private String dob;

    @Column(name = "avatar_link")
    private String avatarLink;

    @Column(name = "fake_id")
    private String fakeId;

    @ManyToMany
    @JoinTable(name = "comics_authors",
               joinColumns = @JoinColumn(name = "author_id"),
               inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics = new LinkedHashSet<>();

    public Set<Comic> getComics() {
        return comics;
    }

    public void setComics(Set<Comic> comics) {
        this.comics = comics;
    }

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
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
}
