package com.comicworld.layer.domain.service.otp.crud;

import com.comicworld.layer.domain.dao.OTPDAO;
import com.comicworld.layer.domain.entity.OTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("otpServiceImplV1")
@Transactional
public class OTPServiceImplV1 implements OTPService {

    @Autowired
    private OTPDAO dao;

    @Override
    public OTP saveOrUpdate(OTP otp) {
        return this.dao.save(otp);
    }

    @Override
    public Optional<OTP> findByCodeWithAllRelationshipsLoadedEagerly(String code) {
        return this.dao.findByCodeWithAllRelationshipsLoadedEagerly(code);
    }

    @Override
    public void delete(OTP otp) {
        this.dao.delete(otp);
    }

    @Override
    public void deleteById(Long id) {
        this.dao.deleteById(id);
    }
}
