package com.comicworld.layer.domain.service.comic.fetch_last_updated_comic;

import com.comicworld.layer.domain.dto.chapter.ChapterOutDTO;
import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.entity.chapter.Chapter;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fetchLastUpdatedComicsServiceImplV1")
public class FetchLastUpdatedComicsServiceImplV1 implements FetchLastUpdatedComicsService {

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Override
    public ResponseEntity<Object> fetch() {
        List<Comic> comics = this.comicService.findTop15LastUpdatedComicsWithChaptersLoadedEagerly();

        List<ComicOutDTO> comicsOutDTO = comics.stream()
                .map(comic -> {
                    Chapter latestChapter = comic.getChapters().iterator().next();
                    latestChapter.setComic(null);
                    comic.setChapters(null);
                    ComicOutDTO comicOutDTO = new ComicOutDTO(comic);
                    comicOutDTO.setLatestChapter(new ChapterOutDTO(latestChapter));
                    return comicOutDTO;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(comicsOutDTO), HttpStatus.OK);
    }
}
