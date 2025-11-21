package com.foodcourt.users_service.infrastructure.output.jpa.adapter;
import com.foodcourt.users_service.domain.port.spi.IPasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class PasswordBcryptAdapter implements IPasswordEncoderPort {


    private final PasswordEncoder passwordEncoder;  //librería

    @Override
    public String encode(String rawPass) {
        return passwordEncoder.encode(rawPass);
    }

    @Override
    public boolean matches(String password, String encodedPass) {

        return passwordEncoder.matches(password, encodedPass);
    }
}
