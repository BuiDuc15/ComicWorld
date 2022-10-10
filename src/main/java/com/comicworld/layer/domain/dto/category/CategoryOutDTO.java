package com.comicworld.layer.domain.dto.category;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.Category;
import org.hibernate.LazyInitializationException;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryOutDTO extends BaseOutDTO {

    private String name;

    private String fakeId;

    private Set<ComicOutDTO> comics;

    public CategoryOutDTO(Category category) {
        this.setId(category.getId());
        this.setName(category.getName());
        this.setFakeId(category.getFakeId());

        try {
            if(category.getComics() != null)
                this.setComics(category.getComics().stream()
                        .map(comic -> new ComicOutDTO(comic))
                        .collect(Collectors.toCollection(LinkedHashSet::new)));
        }
        catch (LazyInitializationException e) {
            System.out.println("COMICS LAZY LOAD FROM CATEGORY OUT DTO");
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

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
    }

}
