package com.foodcourt.users_service.domain.port.spi;

public interface IPasswordEncoderPort {

    String encode(String rawPass);
    boolean matches(String password, String encodedPass);
}
