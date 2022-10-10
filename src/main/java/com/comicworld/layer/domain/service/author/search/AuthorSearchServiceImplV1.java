package com.comicworld.layer.domain.service.author.search;

import com.comicworld.layer.domain.dto.author.AuthorOutDTO;
import com.comicworld.layer.domain.entity.Author;
import com.comicworld.layer.domain.service.author.crud.AuthorService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("authorSearchServiceImplV1")
public class AuthorSearchServiceImplV1 implements AuthorSearchService {

    @Autowired
    @Qualifier("authorServiceImplV1")
    private AuthorService authorService;

    @Override
    public ResponseEntity<Object> search(String keyword) {
        String temp = "";

        if(keyword.length() > 0) {
            keyword = keyword.trim();
            String[] keywords = keyword.split("  *");
            int length = keywords.length - 1;
            for(int i = 0; i < length; ++i) {
                temp += "+" + keywords[i] + " ";
            }
            temp += keywords[keywords.length - 1] + "*";
        }

        keyword = temp;

        List<Author> authors = this.authorService.searchByKeyword(keyword, Env.AUTHOR_SEARCH_LIMIT);

        List<AuthorOutDTO> authorsOutDTO = authors.stream()
                                                .map(author -> new AuthorOutDTO(author))
                                                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                authorsOutDTO
        ), HttpStatus.OK);
    }
}




























