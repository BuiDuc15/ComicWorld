package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.dto.translator_group.TranslatorGroupIdentityInDTO;
import com.comicworld.layer.domain.service.translator_group_identity.add_translator_group_member.AddTranslatorGroupMemberService;
import com.comicworld.layer.domain.service.translator_group_identity.delete_translator_group_identity.DeleteTranslatorGroupIdentityService;
import com.comicworld.layer.domain.service.translator_group_identity.fetch_group_member_info.FetchGroupMemberInfoService;
import com.comicworld.layer.domain.service.translator_group_identity.fetch_group_members.FetchGroupMembersService;
import com.comicworld.layer.domain.service.translator_group_identity.fetch_translator_group_identity_by_id.FetchTranslatorGroupIdentityInfoByIdService;
import com.comicworld.layer.domain.service.translator_group_identity.update_translator_group_identity.UpdateTranslatorGroupIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class TranslatorGroupIdentityControllerV1 {

    @Autowired
    @Qualifier("addTranslatorGroupMemberServiceImplV1")
    private AddTranslatorGroupMemberService addTranslatorGroupMemberService;

    @Autowired
    @Qualifier("updateTranslatorGroupIdentityServiceImplV1")
    private UpdateTranslatorGroupIdentityService updateTranslatorGroupIdentityService;

    @Autowired
    @Qualifier("fetchTranslatorGroupIdentityInfoByIdServiceImplV1")
    private FetchTranslatorGroupIdentityInfoByIdService fetchTranslatorGroupIdentityInfoByIdService;

    @Autowired
    @Qualifier("deleteTranslatorGroupIdentityServiceImplV1")
    private DeleteTranslatorGroupIdentityService deleteTranslatorGroupIdentityService;

    @Autowired
    @Qualifier("fetchGroupMembersServiceImplV1")
    private FetchGroupMembersService fetchGroupMembersService;

    @Autowired
    @Qualifier("fetchGroupMemberInfoServiceImplV1")
    private FetchGroupMemberInfoService fetchGroupMemberInfoService;

    @PostMapping(path = "/v1/translator-groups/{translatorGroupId}/members/{memberId}/translator-group-identities")
    public ResponseEntity<Object> addMemberToTranslatorGroup(@PathVariable("translatorGroupId") Long translatorGroupId,
                                                             @PathVariable("memberId") Long memberId,
                                                             @RequestBody TranslatorGroupIdentityInDTO translatorGroupIdentityInDTO) {
        return this.addTranslatorGroupMemberService.add(translatorGroupId, memberId, translatorGroupIdentityInDTO);
    }

    @PutMapping(path = "/v1/translator-groups/{translatorGroupId}/members/{memberId}/translator-group-identities")
    public ResponseEntity<Object> updateTranslatorGroupIdentity(@RequestBody TranslatorGroupIdentityInDTO translatorGroupIdentityInDTO) {
        return this.updateTranslatorGroupIdentityService.update(translatorGroupIdentityInDTO);
    }

    @GetMapping(path = "/v1/translator-groups/{translatorGroupId}/members/{memberId}/translator-group-identities/{translatorGroupIdentityId}")
    public ResponseEntity<Object> fetchTranslatorGroupIdentityInfo(@PathVariable("translatorGroupIdentityId") Long translatorGroupIdentityId) {
        return this.fetchTranslatorGroupIdentityInfoByIdService.fetch(translatorGroupIdentityId);
    }

    @GetMapping(path = "/v1/translator-groups/{translatorGroupId}/members/translator-group-identities")
    public ResponseEntity<Object> fetchMembersOfTranslatorGroup(@PathVariable("translatorGroupId") Long translatorGroupId) {
        return this.fetchGroupMembersService.fetch(translatorGroupId);
    }

    @GetMapping(path = "/v1/translator-groups/{translatorGroupId}/members/{memberId}/translator-group-identities")
    public ResponseEntity<Object> fetchGroupMemberInfo(@PathVariable("translatorGroupId") Long translatorGroupId,
                                                       @PathVariable("memberId") Long memberId) {
        return this.fetchGroupMemberInfoService.fetch(translatorGroupId, memberId);
    }

    @DeleteMapping(path = "/v1/translator-groups/{translatorGroupId}/members/{memberId}/translator-group-identities/{translatorGroupIdentityId}")
    public ResponseEntity<Object> deleteTranslatorGroupIdentity(@PathVariable("translatorGroupIdentityId") Long translatorGroupIdentityId) {
        return this.deleteTranslatorGroupIdentityService.delete(translatorGroupIdentityId);
    }

}






























