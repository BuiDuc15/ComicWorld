package com.comicworld.layer.domain.service.admin.account;

import com.comicworld.layer.domain.dao.admin_account.AdminAccountDAO;
import com.comicworld.layer.domain.entity.account.AdminAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("adminAccountServiceImplV1")
@Transactional
public class AdminAccountServiceImplV1 implements AdminAccountService {

    @Autowired
    private AdminAccountDAO dao;

    @Override
    public Optional<AdminAccount> findByUsernameWithAuthoritiesLoadedEagerly(String username) {
        return this.dao.findByUsernameWithAuthoritiesLoadedEagerly(username);
    }

    @Override
    public Optional<AdminAccount> findByIdWithAuthoritiesLoadedEagerly(Long id) {
        return this.dao.findByIdWithAuthoritiesLoadedEagerly(id);
    }

    @Override
    public AdminAccount saveOrUpdate(AdminAccount account) {
        return this.dao.save(account);
    }

    @Override
    public Optional<AdminAccount> findByIdWithAllRelationshipsLoadedLazily(Long id) {
        return this.dao.findByIdWithAllRelationshipsLoadedLazily(id);
    }

    @Override
    public List<AdminAccount> searchByKeyword(String keyword, Integer limit) {
        return this.dao.searchByKeyword(keyword, limit);
    }

    @Override
    public void createFullTextIndexByColumn(String column) {
        this.dao.createFullTextIndexByColumn(column);
    }

    @Override
    public Boolean isRelated(Long accountId, Long comicId) {
        return this.dao.isRelated(accountId, comicId);
    }

}
