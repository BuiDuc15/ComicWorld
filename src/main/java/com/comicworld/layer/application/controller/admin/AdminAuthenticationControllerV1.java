package com.comicworld.layer.application.controller.admin;

import com.comicworld.layer.domain.service.admin.refresh_session.AdminRefreshSessionService;
import com.comicworld.layer.domain.service.admin.sign_out.AdminSignOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class AdminAuthenticationControllerV1 {

    @Autowired
    @Qualifier("adminRefreshSessionServiceImplV1")
    private AdminRefreshSessionService adminRefreshSessionService;

    @Autowired
    @Qualifier("adminSignOutServiceImplV1")
    private AdminSignOutService adminSignOutService;

    @PostMapping(path = "/v1/access-token")
    public ResponseEntity<Object> refreshAccessToken(@RequestHeader(value = "refresh-token",
                                                                    required = false,
                                                                    defaultValue = "") String refreshToken) {
        return this.adminRefreshSessionService.refreshSession(refreshToken);
    }

    @PostMapping(path = "/v1/authentication/signout")
    public ResponseEntity<Object> signOut(@RequestHeader(value = "Authorization") String authorizationHeader) {
        return this.adminSignOutService.signOut(authorizationHeader);
    }

}
