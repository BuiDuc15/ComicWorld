package com.comicworld.layer.domain.dto.account;

import com.comicworld.layer.domain.dto.profile.ClientProfileInDTO;
import com.comicworld.layer.domain.entity.account.ClientAccount;

public class ClientAccountInDTO extends AccountInDTO {

    private ClientProfileInDTO clientProfile;

    public ClientAccount toClientAccount() {
        ClientAccount account = new ClientAccount();

        account.setAccountType(getAccountType());
        account.setPassword(getPassword());
        account.setUsername(getUsername());
        account.setId(getId());

        account.setClientProfile(getClientProfile() != null ? getClientProfile().toClientProfile() : null);

        return account;
    }

    public ClientProfileInDTO getClientProfile() {
        return clientProfile;
    }

    public void setClientProfile(ClientProfileInDTO clientProfile) {
        this.clientProfile = clientProfile;
    }
}
