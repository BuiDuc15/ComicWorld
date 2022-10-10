package com.comicworld.layer.domain.service.admin.check_relationship_admin_account_comic_service;

import com.comicworld.layer.domain.service.admin.account.AdminAccountService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("checkRelationshipAdminAccountComicServiceImplV1")
public class CheckRelationshipAdminAccountComicServiceImplV1 implements CheckRelationshipAdminAccountComicService {

    @Autowired
    @Qualifier("adminAccountServiceImplV1")
    private AdminAccountService adminAccountService;

    @Override
    public ResponseEntity<Object> check(Long accountId, Long comicId) {
        Boolean isRelated = this.adminAccountService.isRelated(accountId, comicId);

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                isRelated
        ), HttpStatus.OK);
    }
}
