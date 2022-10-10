package com.comicworld.layer.domain.service.comic.fetch_full_comic_info_by_fake_id;

import com.comicworld.layer.domain.dto.comic.ComicOutDTO;
import com.comicworld.layer.domain.entity.Comic;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("fetchFullComicInfoByFakeIdServiceImplV1")
public class FetchFullComicInfoByFakeIdServiceImplV1 implements FetchFullComicInfoByFakeIdService {

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Override
    public ResponseEntity<Object> fetch(String fakeId) {
        Optional<Comic> rs = this.comicService.findByFakeIdWithAllRelationshipsLoadedEagerly(fakeId);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comic with fake ID " + fakeId + " does exist.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        Comic comic = rs.get();

        comic.getAlternativeNames().forEach(alternativeName -> alternativeName.setComic(null));

        comic.getChapters().forEach(chapter -> chapter.setComic(null));

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                new ComicOutDTO(comic)
        ), HttpStatus.OK);
    }
}
