package com.comicworld.layer.domain.service.black_list_auth_code;

import com.comicworld.layer.domain.entity.BlackListAuthToken;

import java.util.Collection;
import java.util.List;

public interface BlackListAuthTokenService {

    public Boolean existsByCode(String code);

    public BlackListAuthToken saveOrUpdate(BlackListAuthToken token);

    public List<BlackListAuthToken> saveOrUpdateAll(Collection<BlackListAuthToken> tokens);
}
