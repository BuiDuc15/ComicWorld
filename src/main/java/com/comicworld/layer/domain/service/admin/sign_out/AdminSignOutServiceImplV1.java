package com.comicworld.layer.domain.service.admin.sign_out;

import com.comicworld.layer.domain.entity.AuthTokenPair;
import com.comicworld.layer.domain.entity.BlackListAuthToken;
import com.comicworld.layer.domain.service.admin.sign_out.AdminSignOutService;
import com.comicworld.layer.domain.service.auth_token_pair.AuthTokenPairService;
import com.comicworld.layer.domain.service.black_list_auth_code.BlackListAuthTokenService;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service("adminSignOutServiceImplV1")
@Transactional
public class AdminSignOutServiceImplV1 implements AdminSignOutService {

    @Autowired
    @Qualifier("authTokenPairServiceImplV1")
    private AuthTokenPairService authTokenPairService;

    @Autowired
    @Qualifier("blackListAuthTokenServiceImplV1")
    private BlackListAuthTokenService blackListAuthTokenService;

    @Override
    public ResponseEntity<Object> signOut(String authorizationHeader) {

        String accessToken = authorizationHeader.substring("Bearer ".length());

        Optional<AuthTokenPair> rs = this.authTokenPairService.findByAccessToken(accessToken);

        AuthTokenPair authTokenPair = rs.get();

        BlackListAuthToken blackListAccessToken = new BlackListAuthToken();
        blackListAccessToken.setCode(authTokenPair.getAccessToken());

        BlackListAuthToken blackListRefreshToken = new BlackListAuthToken();
        blackListRefreshToken.setCode(authTokenPair.getRefreshToken());

        this.blackListAuthTokenService.saveOrUpdateAll(Arrays.asList(
                blackListAccessToken,
                blackListRefreshToken
        ));

        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseEntity<>(
                ResponseBodyFactoryV1.succeededResponseBody(),
                HttpStatus.OK
        );
    }

}
