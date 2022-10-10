package com.comicworld.layer.domain.service.media.handler;

import com.comicworld.layer.domain.model.MediaModel;
import com.comicworld.layer.domain.exception.IllegalMediaException;

public interface MediaHandler {

    public String handle(MediaModel mediaModel) throws IllegalMediaException;

}
