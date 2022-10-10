package com.comicworld.layer.domain.service.author.fetch_authors_of_comic;

import com.comicworld.layer.domain.dto.author.AuthorOutDTO;
import com.comicworld.layer.domain.entity.Author;
import com.comicworld.layer.domain.service.author.crud.AuthorService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fetchAuthorsOfComicServiceImplV1")
public class FetchAuthorsOfComicServiceImplV1 implements FetchAuthorsOfComicService {

    @Autowired
    @Qualifier("authorServiceImplV1")
    private AuthorService authorService;

    @Override
    public ResponseEntity<Object> fetch(Long comicId) {
        List<Author> authors = this.authorService.findByComicId(comicId);

        List<AuthorOutDTO> authorsOutDTO = authors.stream()
                .map(author -> new AuthorOutDTO(author))
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                authorsOutDTO
        ), HttpStatus.OK);
    }
}
