package com.comicworld.layer.domain.service.client.account;

import com.comicworld.layer.domain.entity.account.ClientAccount;

import java.util.Optional;

public interface ClientAccountService {

    public Optional<ClientAccount> findByUsernameWithAuthoritiesLoadedEagerly(String username);

    public Optional<ClientAccount> findByUsernameWithAuthoritiesLoadedLazily(String username);

    public Optional<ClientAccount> findByIdWithAuthoritiesLoadedEagerly(Long id);

    public Optional<ClientAccount> findByIdWithAllRelationshipsLoadedLazily(Long id);

    public ClientAccount saveOrUpdate(ClientAccount clientAccount);

    public Boolean existsByUsername(String username);

}
