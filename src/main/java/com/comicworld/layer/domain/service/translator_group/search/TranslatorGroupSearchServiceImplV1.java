package com.comicworld.layer.domain.service.translator_group.search;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupOutDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("translatorGroupSearchServiceImplV1")
public class TranslatorGroupSearchServiceImplV1 implements TranslatorGroupSearchService {

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

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

        List<TranslatorGroup> rs = this.translatorGroupService.searchByKeyword(keyword, Env.TRANSLATOR_GROUP_SEARCH_LIMIT);

        List<TranslatorGroupOutDTO> translatorGroupsOutDTO = rs.stream()
                .map(translatorGroup -> new TranslatorGroupOutDTO(translatorGroup))
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                translatorGroupsOutDTO
        ), HttpStatus.OK);
    }

}
