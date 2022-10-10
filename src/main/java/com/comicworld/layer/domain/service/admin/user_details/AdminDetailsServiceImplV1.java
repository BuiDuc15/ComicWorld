package com.comicworld.layer.domain.service.admin.user_details;

import com.comicworld.layer.domain.entity.account.AdminAccount;
import com.comicworld.layer.domain.model.user_details.AdminDetailsModelV1;
import com.comicworld.layer.domain.service.admin.account.AdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("adminDetailsServiceImplV1")
public class AdminDetailsServiceImplV1 implements UserDetailsService {

    @Autowired
    @Qualifier("adminAccountServiceImplV1")
    private AdminAccountService adminAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AdminAccount> rs = this.adminAccountService.findByUsernameWithAuthoritiesLoadedEagerly(username);

        if(rs.isEmpty()) throw new UsernameNotFoundException("Account with username '" + username + "' is not found.");

        return new AdminDetailsModelV1(rs.get());
    }

}
