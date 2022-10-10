package com.comicworld.layer.domain.service.admin.account;

import com.comicworld.layer.domain.entity.account.AdminAccount;

import java.util.List;
import java.util.Optional;

public interface AdminAccountService {

    public Optional<AdminAccount> findByUsernameWithAuthoritiesLoadedEagerly(String username);

    public Optional<AdminAccount> findByIdWithAuthoritiesLoadedEagerly(Long id);

    public AdminAccount saveOrUpdate(AdminAccount account);

    public Optional<AdminAccount> findByIdWithAllRelationshipsLoadedLazily(Long id);

    public List<AdminAccount> searchByKeyword(String keyword, Integer limit);

    public void createFullTextIndexByColumn(String column);

    public Boolean isRelated(Long accountId, Long comicId);

}
