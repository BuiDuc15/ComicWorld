package com.comicworld.layer.domain.service.comic.compartor;

import com.comicworld.layer.domain.entity.Comic;

import java.util.Comparator;

public class ComicLastUpdateDescComparator implements Comparator<Comic> {

    @Override
    public int compare(Comic o1, Comic o2) {
        return o2.getLastUpdatedAt().compareTo(o1.getLastUpdatedAt());
    }
}
