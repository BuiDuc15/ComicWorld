package com.comicworld.layer.domain.service.admin.profile;

import com.comicworld.layer.domain.entity.profile.AdminProfile;

import java.util.Optional;

public interface AdminProfileService {

    public AdminProfile saveOrUpdate(AdminProfile profile);

    public Optional<AdminProfile> findByAccountId(Long accountId);

}
