package com.comicworld.layer.domain.service.ckeditor;

import com.comicworld.layer.domain.exception.IllegalMediaException;
import com.comicworld.layer.domain.model.CKEditorResponseModel;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.aws_s3.AWSS3Service;
import com.comicworld.utils.Env;
import com.comicworld.utils.StringUtils;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("ckEditorServiceImplV1")
public class CKEditorServiceImplV1 implements CKEditorService {

    @Autowired
    @Qualifier("awsS3ServiceImplV1")
    private AWSS3Service awss3Service;

    @Autowired
    private Tika tika;

    @Override
    public ResponseEntity<Object> upload(MultipartFile multipartFile) {

        if(!this.isImage(multipartFile))
            return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                    new FailedBodyContentV1(Env.ILLEGAL_MEDIA_EXCEPTION_TYPE, multipartFile.getOriginalFilename() + " is not an image.")
            ), HttpStatus.UNPROCESSABLE_ENTITY);

        String url = this.awss3Service.uploadFileToStaticFolder(multipartFile);

        return new ResponseEntity<>(new CKEditorResponseModel(url, true, multipartFile.getOriginalFilename()),
                                    HttpStatus.OK);
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
