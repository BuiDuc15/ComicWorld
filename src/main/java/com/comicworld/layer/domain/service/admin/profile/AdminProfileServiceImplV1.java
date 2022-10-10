package com.comicworld.layer.domain.service.admin.profile;

import com.comicworld.layer.domain.dao.AdminProfileDAO;
import com.comicworld.layer.domain.entity.profile.AdminProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("adminProfileServiceImplV1")
@Transactional
public class AdminProfileServiceImplV1 implements AdminProfileService {

    @Autowired
    private AdminProfileDAO dao;

    @Override
    public AdminProfile saveOrUpdate(AdminProfile profile) {
        return this.dao.save(profile);
    }

    @Override
    public Optional<AdminProfile> findByAccountId(Long accountId) {
        return this.dao.findByAccountId(accountId);
    }
}
