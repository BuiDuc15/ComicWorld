package com.comicworld.layer.domain.dto.alternative_name;

import com.comicworld.layer.domain.dto.base.BaseOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.AlternativeName;
import org.hibernate.LazyInitializationException;

public class AlternativeNameOutDTO extends BaseOutDTO {

    private String name;

    private ComicOutDTO comic;

    public AlternativeNameOutDTO(AlternativeName alternativeName) {
        this.setName(alternativeName.getName());
        this.setId(alternativeName.getId());

        try {
            if(alternativeName.getComic() != null)
                this.setComic(new ComicOutDTO(alternativeName.getComic()));
        }
        catch (LazyInitializationException e) {
            System.out.println("COMIC LAZY LOAD FROM ALTERNATIVE NAME OUT DTO");
        }
    }

    public ComicOutDTO getComic() {
        return comic;
    }

    public void setComic(ComicOutDTO comic) {
        this.comic = comic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
