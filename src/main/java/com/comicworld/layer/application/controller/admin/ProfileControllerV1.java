package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.service.admin.get_profile.AdminGetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class ProfileControllerV1 {

    @Autowired
    @Qualifier("adminGetProfileServiceImplV1")
    private AdminGetProfileService adminGetProfileService;

    @GetMapping(path = "/v1/accounts/{accountId}/profile")
    public ResponseEntity<Object> getProfileByAccountId(@PathVariable("accountId") Long accountId) {
        return this.adminGetProfileService.getByAccountId(accountId);
    }

}
