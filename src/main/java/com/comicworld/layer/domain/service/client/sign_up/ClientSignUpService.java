package com.comicworld.layer.domain.service.client.sign_up;

import com.comicworld.layer.domain.dto.account.ClientAccountInDTO;
import org.springframework.http.ResponseEntity;

public interface ClientSignUpService {

    public ResponseEntity<Object> signUp(ClientAccountInDTO clientAccountInDTO);

}
