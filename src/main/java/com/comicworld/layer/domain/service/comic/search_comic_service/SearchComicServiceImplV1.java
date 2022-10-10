package com.comicworld.layer.domain.service.comic.search_comic_service;

import com.comicworld.layer.domain.dto.chapter.ChapterOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.AlternativeName;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("searchComicServiceImplV1")
public class SearchComicServiceImplV1 implements SearchComicService {

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Override
    public ResponseEntity<Object> search(String keyword) {
        String temp = "";

        if(keyword.length() > 0) {
            keyword = keyword.trim();
            String[] keywords = keyword.split("  *");
            int length = keywords.length - 1;
            for(int i = 0; i < length; ++i) {
                temp += "+" + keywords[i] + " ";
            }
            temp += keywords[keywords.length - 1] + "*";
        }

        keyword = temp;

        List<AlternativeName> alternativeNames = this.alternativeNameService.searchByName(keyword, Env.COMIC_SEARCH_LIMIT);

        List<Long> alternativeNameIds = alternativeNames.stream()
                                                        .map(alternativeName -> alternativeName.getId())
                                                        .collect(Collectors.toList());

        Set<Comic> comics = Sets.newLinkedHashSet(this.comicService.findByAlternativeNameIdsInWithChaptersLoadedEagerly(alternativeNameIds));

        List<ComicOutDTO> comicsOutDTO = new ArrayList<>();

        comics.forEach(comic -> {
            Iterator<Chapter> iterator = comic.getChapters().iterator();
            Chapter latestChapter = null;
            if(iterator.hasNext()) {
                latestChapter = iterator.next();
                latestChapter.setComic(null);
            }
            comic.setChapters(null);
            ComicOutDTO comicOutDTO = new ComicOutDTO(comic);
            if(latestChapter != null) comicOutDTO.setLatestChapter(new ChapterOutDTO(latestChapter));
            comicsOutDTO.add(comicOutDTO);
        });

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(comicsOutDTO), HttpStatus.OK);
    }

}
