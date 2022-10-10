package com.comicworld.layer.domain.service.client.profile.crud;

import com.comicworld.layer.domain.entity.profile.ClientProfile;

import java.util.Optional;

public interface ClientProfileService {

    public Optional<ClientProfile> findByAccountId(Long id);

    public ClientProfile saveOrUpdate(ClientProfile clientProfile);

}
