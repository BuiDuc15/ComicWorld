package com.comicworld.layer.domain.service.media;

import com.comicworld.layer.domain.model.MediaModel;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.media.handler.MediaHandler;
import com.comicworld.layer.domain.exception.IllegalMediaException;
import com.comicworld.layer.domain.exception.UnsupportedMediaTypeException;
import com.comicworld.utils.Env;
import com.comicworld.utils.media_handler_factory.MediaHandlerFactoryV1;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("mediaServiceImplV1")
public class MediaServiceImplV1 implements MediaService {

    @Autowired
    private MediaHandlerFactoryV1 mediaHandlerFactoryV1;

    @Override
    public ResponseEntity<Object> process(MediaModel mediaModel) {

        try {
            MediaHandler mediaHandler = this.mediaHandlerFactoryV1.getMediaHandlerBasedOnMediaType(mediaModel.getMediaType());

            String link = mediaHandler.handle(mediaModel);

            HttpHeaders headers = new HttpHeaders();

            headers.set("source-link", link);

            return new ResponseEntity<>(ResponseBodyFactoryV1.createdResponseBody(), headers, HttpStatus.CREATED);
        }
        catch (UnsupportedMediaTypeException e) {
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.UNSUPPORTED_MEDIA_TYPE_EXCEPTION_TYPE, e.getMessage())
            ), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (IllegalMediaException e) {
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.ILLEGAL_MEDIA_EXCEPTION_TYPE, e.getMessage())
            ), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
