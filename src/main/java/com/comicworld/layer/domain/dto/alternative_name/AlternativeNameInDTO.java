package com.comicworld.layer.domain.dto.alternative_name;

import com.comicworld.layer.domain.dto.base.BaseInDTO;
import com.comicworld.layer.domain.dto.comic.ComicInDTO;
import com.comicworld.layer.domain.entity.AlternativeName;

public class AlternativeNameInDTO extends BaseInDTO {

    private String name;

    private ComicInDTO comic;

    public AlternativeName toAlternativeName() {
        AlternativeName alternativeName = new AlternativeName();

        alternativeName.setName(getName());
        alternativeName.setId(getId());

        alternativeName.setComic(getComic() != null ? getComic().toComic() : null);

        return alternativeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComicInDTO getComic() {
        return comic;
    }

    public void setComic(ComicInDTO comic) {
        this.comic = comic;
    }
}
