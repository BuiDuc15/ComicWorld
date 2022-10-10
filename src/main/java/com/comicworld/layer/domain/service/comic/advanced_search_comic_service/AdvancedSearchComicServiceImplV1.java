package com.comicworld.layer.domain.service.comic.advanced_search_comic_service;

import com.comicworld.layer.domain.dto.chapter.ChapterOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.AlternativeName;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.model.PageModel;
import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.layer.domain.service.comic.compartor.*;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("advancedSearchComicServiceImplV1")
public class AdvancedSearchComicServiceImplV1 implements AdvancedSearchComicService {

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Override
    public ResponseEntity<Object> search(Map<String, String[]> params) {
        Map<String, Object> queryParams = new HashMap<>();
        Integer page = 0;
        String keyword = params.containsKey("keyword") ? (String) params.get("keyword")[0] : "";
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

        try {
            page = params.containsKey("page") ? Integer.valueOf(params.get("page")[0]) : 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            page = 0;
        }

        Integer offset = page * Env.DEFAULT_PAGE_SIZE;

        Integer limit = offset + Env.DEFAULT_PAGE_SIZE;

        queryParams.put("keyword", keyword);

        List<AlternativeName> fullAlternativeNames = this.alternativeNameService.searchFullByName(queryParams);

        List<Long> fullAlternativeNameIds = fullAlternativeNames.stream()
                .map(alternativeName -> alternativeName.getId())
                .collect(Collectors.toList());

        List<Comic> fullComics = Lists.newArrayList(Sets.newLinkedHashSet(this.comicService.findByAlternativeNameIdsInWithChaptersLoadedEagerly(fullAlternativeNameIds)));

        if(params.containsKey("name")) {
            String nameSortOption = (String) params.get("name")[0];
            if(nameSortOption.equalsIgnoreCase("DESC"))
                Collections.sort(fullComics, new ComicDisplayedNameDescComparator());
            else Collections.sort(fullComics, new ComicDisplayedNameAscComparator());
        }

        if(params.containsKey("view")) {
            String viewSortOption = (String) params.get("view")[0];
            if(viewSortOption.equalsIgnoreCase("DESC"))
                Collections.sort(fullComics, new ComicViewDescComparator());
            else Collections.sort(fullComics, new ComicViewAscComparator());
        }

        if(params.containsKey("lastUpdate")) {
            String lastUpdateSortOption = (String) params.get("lastUpdate")[0];
            if(lastUpdateSortOption.equalsIgnoreCase("DESC"))
                Collections.sort(fullComics, new ComicLastUpdateDescComparator());
            else Collections.sort(fullComics, new ComicLastUpdateAscComparator());
        }

        limit = limit > fullComics.size() ? fullComics.size() : limit;

        List<Comic> comics = fullComics.subList(offset, limit);

        Integer totalElements = fullComics.size();

        Long totalPages = Long.valueOf(totalElements % Env.DEFAULT_PAGE_SIZE == 0 ?
                                totalElements / Env.DEFAULT_PAGE_SIZE : totalElements / Env.DEFAULT_PAGE_SIZE + 1);

        List<ComicOutDTO> comicsOutDTO = new ArrayList<>();

        comics.forEach(comic -> {
            Chapter latestChapter = comic.getChapters().iterator().next();
            latestChapter.setComic(null);
            comic.setChapters(null);
            ComicOutDTO comicOutDTO = new ComicOutDTO(comic);
            comicOutDTO.setLatestChapter(new ChapterOutDTO(latestChapter));
            comicsOutDTO.add(comicOutDTO);
        });

        return new ResponseEntity<>(
                ResponseBodyFactoryV1.succeededResponseBody(new PageModel(page, totalPages, comicsOutDTO)),
                HttpStatus.OK);
    }
}


































