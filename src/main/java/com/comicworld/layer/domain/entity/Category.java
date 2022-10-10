package com.comicworld.layer.domain.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "categories")
public class Category extends Base {

    @Column(name = "name")
    private String name;

    @Column(name = "fake_id")
    private String fakeId;

    @ManyToMany
    @JoinTable(name = "comics_categories",
               joinColumns = @JoinColumn(name = "category_id"),
               inverseJoinColumns = @JoinColumn(name = "comic_id"))
    private Set<Comic> comics = new LinkedHashSet<>();

    public Set<Comic> getComics() {
        return comics;
    }

    public void setComics(Set<Comic> comics) {
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
}

