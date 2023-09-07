package com.coupang.marketplace.user.service;

import com.coupang.marketplace.global.SessionKey;
import com.coupang.marketplace.user.controller.dto.LoginRequestDTO;
import com.coupang.marketplace.user.domain.User;
import com.coupang.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void login(LoginRequestDTO dto) {
        if(!userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }
        //DB에 있는 데이터 가져와서 비밀번호 검증
        Optional<User> user = userRepository.findByEmail(dto.getEmail());

        log.info("verification password {}", dto.getPassword());
        log.info("encodedPassword {}", user.get().getPassword());
        log.info("matches boolean {}",passwordEncoder.matches(dto.getPassword(), user.get().getPassword()));
        if(!passwordEncoder.matches(dto.getPassword(), user.get().getPassword())){
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }
        httpSession.setAttribute(SessionKey.LOGIN_USER_ID, user.get().getId());
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(SessionKey.LOGIN_USER_ID);
    }

    @Override
    public long getLoginUserId() {
        return (long) httpSession.getAttribute(SessionKey.LOGIN_USER_ID);
    }
}
