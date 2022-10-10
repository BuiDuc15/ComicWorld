package com.comicworld.layer.domain.service.comic.fetch_comic_info_service;

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

@Service("fetchComicInfoServiceImplV1")
public class FetchComicInfoServiceImplV1 implements FetchComicInfoService {

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Override
    public ResponseEntity<Object> fetch(Long id) {
        Optional<Comic> rs = this.comicService.findByIdWithAllRelationshipsLoadedLazily(id);

        if(rs.isEmpty())
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(new FailedBodyContentV1(
                    Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comic with ID " + id + " does not exists."
            )), HttpStatus.UNPROCESSABLE_ENTITY);

        Comic comic = rs.get();

        ComicOutDTO comicOutDTO = new ComicOutDTO(comic);

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(comicOutDTO), HttpStatus.OK);
    }
}
