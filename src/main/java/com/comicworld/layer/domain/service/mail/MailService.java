package com.comicworld.layer.domain.service.mail;

import java.util.Map;

public interface MailService {

    public void sendMail(Map<String, Object> attributes);

}
