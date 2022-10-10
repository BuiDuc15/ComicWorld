package com.comicworld.layer.domain.service.black_list_auth_code;

import com.comicworld.layer.domain.dao.BlackListAuthTokenDAO;
import com.comicworld.layer.domain.entity.BlackListAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service("blackListAuthTokenServiceImplV1")
@Transactional
public class BlackListAuthTokenServiceImplV1 implements BlackListAuthTokenService {

    @Autowired
    private BlackListAuthTokenDAO dao;

    @Override
    public Boolean existsByCode(String code) {
        return this.dao.existsByCode(code);
    }

    @Override
    public BlackListAuthToken saveOrUpdate(BlackListAuthToken token) {
        return this.dao.save(token);
    }

    @Override
    public List<BlackListAuthToken> saveOrUpdateAll(Collection<BlackListAuthToken> tokens) {
        return this.dao.saveAll(tokens);
    }

}
