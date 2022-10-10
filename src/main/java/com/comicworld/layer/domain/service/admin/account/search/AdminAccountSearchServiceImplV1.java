package com.comicworld.layer.domain.service.admin.account.search;

import com.comicworld.layer.domain.dto.account.AdminAccountOutDTO;
import com.comicworld.layer.domain.entity.account.AdminAccount;
import com.comicworld.layer.domain.service.admin.account.AdminAccountService;
import com.comicworld.utils.Env;
import com.comicworld.utils.response_body_factory.ResponseBodyFactoryV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("adminAccountSearchServiceImplV1")
public class AdminAccountSearchServiceImplV1 implements AdminAccountSearchService {

    @Autowired
    @Qualifier("adminAccountServiceImplV1")
    private AdminAccountService adminAccountService;

    @Override
    public ResponseEntity<Object> search(String keyword) {
        if(!keyword.isEmpty()) keyword = keyword.replaceAll("  *", "") + "*";

        List<AdminAccount> rs = this.adminAccountService.searchByKeyword(keyword, Env.ACCOUNT_SEARCH_LIMIT);

        List<AdminAccountOutDTO> adminAccountsOutDTO = rs.stream()
                .map(adminAccount -> {
                    adminAccount.getProfile().setAccount(null);
                    return new AdminAccountOutDTO(adminAccount);
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBodyFactoryV1.succeededResponseBody(
                adminAccountsOutDTO
        ), HttpStatus.OK);
    }

}
