package com.comicworld.layer.domain.service.translator_group.fetch_translator_group_of_member;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupOutDTO;
import com.comicworld.layer.domain.entity.translator_group.TranslatorGroup;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("fetchTranslatorGroupsOfMemberServiceImplV1")
public class FetchTranslatorGroupsOfMemberServiceImplV1 implements FetchTranslatorGroupsOfMemberService {

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

    @Override
    public ResponseEntity<Object> fetch(Long memberId) {

        List<TranslatorGroup> rs = this.translatorGroupService.findByMemberId(memberId);

        List<TranslatorGroupOutDTO> translatorGroupsOutDTO = rs.stream()
                .map(translatorGroup -> new TranslatorGroupOutDTO(translatorGroup))
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                translatorGroupsOutDTO
        ), HttpStatus.OK);
    }

}
