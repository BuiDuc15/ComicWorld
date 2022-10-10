package com.comicworld.utils.media_handler_factory;

import com.comicworld.layer.domain.service.media.handler.MediaHandler;
import com.comicworld.layer.domain.exception.UnsupportedMediaTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MediaHandlerFactoryV1 {

    @Autowired
    @Qualifier("imageHandler")
    private MediaHandler mediaHandler;

    public MediaHandler getMediaHandlerBasedOnMediaType(String mediaType) throws UnsupportedMediaTypeException {

        if(mediaType.equalsIgnoreCase("IMAGE"))
            return this.mediaHandler;

        throw new UnsupportedMediaTypeException(mediaType + " type is not supported.");
    }

}
