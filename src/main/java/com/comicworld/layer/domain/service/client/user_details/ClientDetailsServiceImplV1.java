package com.comicworld.layer.domain.service.client.user_details;

import com.comicworld.layer.domain.entity.account.ClientAccount;
import com.comicworld.layer.domain.model.user_details.ClientDetailsModelV1;
import com.comicworld.layer.domain.service.client.account.ClientAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("clientDetailsServiceImplV1")
public class ClientDetailsServiceImplV1 implements UserDetailsService {

    @Autowired
    @Qualifier("clientAccountServiceImplV1")
    private ClientAccountService clientAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClientAccount> rs = this.clientAccountService.findByUsernameWithAuthoritiesLoadedEagerly(username);

        if(rs.isEmpty()) throw new UsernameNotFoundException("Account with username " + username + " is not found.");

        return new ClientDetailsModelV1(rs.get());
    }
}
