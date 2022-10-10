package com.comicworld.layer.domain.service.media.handler;

import com.comicworld.layer.domain.model.MediaModel;
import com.comicworld.layer.domain.service.aws_s3.AWSS3Service;
import com.comicworld.layer.domain.exception.IllegalMediaException;
import com.comicworld.utils.StringUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("imageHandler")
public class ImageHandler implements MediaHandler {

    @Autowired
    @Qualifier("awsS3ServiceImplV1")
    private AWSS3Service awss3Service;

    @Autowired
    private Tika tika;

    @Override
    public String handle(MediaModel mediaModel) throws IllegalMediaException {

        MultipartFile multipartFile = mediaModel.getMultipartFile();

        if(!this.isImage(multipartFile))
            throw new IllegalMediaException(multipartFile.getOriginalFilename() + " is not an image.");

        return this.awss3Service.uploadFileToStaticFolder(multipartFile);
    }

    private boolean isImage(MultipartFile multipartFile) {
        try {
            String mime = this.tika.detect(multipartFile.getInputStream(), StringUtils.purify(multipartFile.getOriginalFilename()));
            return mime.contains("/")
                    && mime.split("/")[0].equalsIgnoreCase("image");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
