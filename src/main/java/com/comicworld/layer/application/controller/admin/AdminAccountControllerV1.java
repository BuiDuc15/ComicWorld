package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.service.admin.account.search.AdminAccountSearchService;
import com.comicworld.layer.domain.service.admin.check_relationship_admin_account_comic_service.CheckRelationshipAdminAccountComicService;
import com.comicworld.layer.domain.service.translator_group.fetch_translator_group_of_member.FetchTranslatorGroupsOfMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")
public class AdminAccountControllerV1 {

    @Autowired
    @Qualifier("adminAccountSearchServiceImplV1")
    private AdminAccountSearchService adminAccountSearchService;

    @Autowired
    @Qualifier("fetchTranslatorGroupsOfMemberServiceImplV1")
    private FetchTranslatorGroupsOfMemberService fetchTranslatorGroupsOfMemberService;

    @Autowired
    @Qualifier("checkRelationshipAdminAccountComicServiceImplV1")
    private CheckRelationshipAdminAccountComicService checkRelationshipAdminAccountComicService;

    @GetMapping(path = "/v1/admin-accounts/search")
    public ResponseEntity<Object> searchByKeyword(@RequestParam("keyword") String keyword) {
        return this.adminAccountSearchService.search(keyword);
    }

    @GetMapping(path = "/v1/admin-accounts/{accountId}/translator-groups")
    public ResponseEntity<Object> fetchTranslatorGroupsOfAccount(@PathVariable("accountId") Long memberId) {
        return this.fetchTranslatorGroupsOfMemberService.fetch(memberId);
    }

    @GetMapping(path = "/v1/admin-accounts/{accountId}/comics/{comicId}/relationship")
    public ResponseEntity<Object> checkRelationshipBetweenAdminAccountAndComic(@PathVariable("accountId") Long accountId,
                                                                               @PathVariable("comicId") Long comicId) {
        return this.checkRelationshipAdminAccountComicService.check(accountId, comicId);
    }

}
