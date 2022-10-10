package com.comicworld.layer.domain.service.authority;

import com.comicworld.layer.domain.entity.Authority;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorityService {

    public Authority saveOrUpdate(Authority authority);

    public List<Authority> saveOrUpdateAll(Collection<Authority> authorities);

    public Set<Authority> findByAdminAccountId(Long id);

    public Set<Authority> findByClientAccountId(Long id);

    public Optional<Authority> findByIdWithAdminAccountsLoadedEagerly(Long id);

    public Optional<Authority> findByRoleWithAdminAccountsLoadedEagerly(String role);

    public Optional<Authority> findByRoleWithClientAccountsLoadedEagerly(String role);

}
