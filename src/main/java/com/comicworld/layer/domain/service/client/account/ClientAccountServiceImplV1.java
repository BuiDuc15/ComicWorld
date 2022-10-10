package com.comicworld.layer.domain.service.client.account;

import com.comicworld.layer.domain.dao.client_account.ClientAccountDAO;
import com.comicworld.layer.domain.entity.account.ClientAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("clientAccountServiceImplV1")
@Transactional
public class ClientAccountServiceImplV1 implements ClientAccountService {

    @Autowired
    private ClientAccountDAO dao;

    @Override
    public Optional<ClientAccount> findByUsernameWithAuthoritiesLoadedEagerly(String username) {
        return this.dao.findByUsernameWithAuthoritiesLoadedEagerly(username);
    }

    @Override
    public Optional<ClientAccount> findByUsernameWithAuthoritiesLoadedLazily(String username) {
        return this.dao.findByUsernameWithAuthoritiesLoadedLazily(username);
    }

    @Override
    public Optional<ClientAccount> findByIdWithAuthoritiesLoadedEagerly(Long id) {
        return this.dao.findByIdWithAuthoritiesLoadedEagerly(id);
    }

    @Override
    public Optional<ClientAccount> findByIdWithAllRelationshipsLoadedLazily(Long id) {
        return this.dao.findByIdWithAllRelationshipsLoadedLazily(id);
    }

    @Override
    public ClientAccount saveOrUpdate(ClientAccount clientAccount) {
        return this.dao.save(clientAccount);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return this.dao.existsByUsername(username);
    }
}
