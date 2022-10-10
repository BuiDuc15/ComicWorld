package com.comicworld.layer.domain.service.authority;

import com.comicworld.layer.domain.dao.AuthorityDAO;
import com.comicworld.layer.domain.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("authorityServiceImplV1")
@Transactional
public class AuthorityServiceImplV1 implements AuthorityService {

    @Autowired
    private AuthorityDAO dao;

    @Override
    public Authority saveOrUpdate(Authority authority) {
        return this.dao.save(authority);
    }

    @Override
    public List<Authority> saveOrUpdateAll(Collection<Authority> authorities) {
        return this.dao.saveAll(authorities);
    }

    @Override
    public Set<Authority> findByAdminAccountId(Long id) {
        return this.dao.findByAdminAccountId(id);
    }

    @Override
    public Set<Authority> findByClientAccountId(Long id) {
        return this.dao.findByClientAccountId(id);
    }

    @Override
    public Optional<Authority> findByIdWithAdminAccountsLoadedEagerly(Long id) {
        return this.dao.findByIdWithAdminAccountsLoadedEagerly(id);
    }

    @Override
    public Optional<Authority> findByRoleWithAdminAccountsLoadedEagerly(String role) {
        return this.dao.findByRoleWithAdminAccountsLoadedEagerly(role);
    }

    @Override
    public Optional<Authority> findByRoleWithClientAccountsLoadedEagerly(String role) {
        return this.dao.findByRoleWithClientAccountsLoadedEagerly(role);
    }

}
