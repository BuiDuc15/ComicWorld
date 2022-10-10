package com.comicworld.layer.domain.service.admin.get_profile;

import com.comicworld.layer.domain.dto.profile.AdminProfileOutDTO;
import com.comicworld.layer.domain.entity.profile.AdminProfile;
import com.comicworld.layer.domain.model.failed_body_content.FailedBodyContentV1;
import com.comicworld.layer.domain.service.admin.profile.AdminProfileService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("adminGetProfileServiceImplV1")
public class AdminGetProfileServiceImplV1 implements AdminGetProfileService {

    @Autowired
    @Qualifier("adminProfileServiceImplV1")
    private AdminProfileService adminProfileService;

    @Override
    public ResponseEntity<Object> getByAccountId(Long id) {

        Optional<AdminProfile> rs = this.adminProfileService.findByAccountId(id);

        if(rs.isEmpty())
            return new ResponseEntity<>(
                    ResponseBodyFactoryV1.unprocessableResponseBody(new FailedBodyContentV1(
                            Env.UNPROCESSABLE_EXCEPTION_TYPE,
                            "Profile of account with ID " + id + " is not found."
                    )),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );

        AdminProfile adminProfile = rs.get();

        adminProfile.setAccount(null);

        return new ResponseEntity<>(
                ResponseBodyFactoryV1.succeededResponseBody(
                        new AdminProfileOutDTO(adminProfile)
                ),
                HttpStatus.OK
        );
    }

}
