package com.comicworld.layer.domain.dto.category;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.entity.Category;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryInDTO extends BaseInDTO {

    private String name;

    private String fakeId;

    private Set<ComicInDTO> comics;

    public Category toCategory() {
        Category category = new Category();

        category.setName(getName());
        category.setFakeId(getFakeId());
        category.setId(getId());

        category.setComics(getComics() != null ?
                getComics().stream()
                        .map(comic -> comic.toComic())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : null);

        return category;
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
}
