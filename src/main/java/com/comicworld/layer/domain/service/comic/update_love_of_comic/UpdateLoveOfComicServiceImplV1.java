package com.comicworld.layer.domain.service.comic.update_love_of_comic;

import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.comic.crud.ComicService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;

@Service("updateLoveOfComicServiceImplV1")
@Transactional
public class UpdateLoveOfComicServiceImplV1 implements UpdateLoveOfComicService {

    @Autowired
    @Qualifier("comicServiceImplV1")
    private ComicService comicService;

    @Override
    public ResponseEntity<Object> update(Long id, String action) {
        boolean status = true;

        while(status) {
            try {
                this.comicService.updateLoveOfComicById(id, action);
                status = false;
            }
            catch (OptimisticLockException e) {
                e.printStackTrace();
            }
            catch (NoResultException e) {
                return new ResponseEntity<>(ResponseBodyFactoryV1.unprocessableResponseBody(
                        new FailedBodyContentV1(Env.UNPROCESSABLE_EXCEPTION_TYPE, "Comic with ID " + id + " does not exist.")
                ), HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }

        HttpHeaders headers = new HttpHeaders();

        headers.set("id", id.toString());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(), headers, HttpStatus.OK);
    }
}
















































