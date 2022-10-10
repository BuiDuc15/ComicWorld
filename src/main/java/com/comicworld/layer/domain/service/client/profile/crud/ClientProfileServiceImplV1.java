package com.comicworld.layer.domain.service.client.profile.crud;

import com.comicworld.layer.domain.dao.ClientProfileDAO;
import com.comicworld.layer.domain.entity.profile.ClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("clientProfileServiceImplV1")
@Transactional
public class ClientProfileServiceImplV1 implements ClientProfileService {

    @Autowired
    private ClientProfileDAO dao;

    @Override
    public Optional<ClientProfile> findByAccountId(Long id) {
        return this.dao.findByAccountId(id);
    }

    @Override
    public ClientProfile saveOrUpdate(ClientProfile clientProfile) {
        return this.dao.save(clientProfile);
    }
}
