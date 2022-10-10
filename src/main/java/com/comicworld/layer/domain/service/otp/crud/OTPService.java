package com.comicworld.layer.domain.service.otp.crud;

import com.comicworld.layer.domain.entity.OTP;

import java.util.Optional;

public interface OTPService {

    public OTP saveOrUpdate(OTP otp);

    public Optional<OTP> findByCodeWithAllRelationshipsLoadedEagerly(String code);

    public void delete(OTP otp);

    public void deleteById(Long id);

}
