package com.coupang.marketplace.user.service;

import com.coupang.marketplace.user.controller.dto.SignUpRequestDTO;
import com.coupang.marketplace.user.domain.User;
import com.coupang.marketplace.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void join(SignUpRequestDTO requestDto) {

        //동일한 유저 있는지 체크 로직 필수 parameter -> requestDto.email
        if (checkIsUserExist(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 등록된 메일입니다.");
        }
        //비밀번호 암호화 로직 필수
        String encode = passwordEncoder.encode(requestDto.getPassword());

        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(encode)
                .phone(requestDto.getPhone())
                .build();
        userRepository.insertUser(user);

    }
    public boolean checkIsUserExist(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public User userInfoResponse(long loginUserId){
        return userRepository.findByUserInfo(loginUserId);
    }
}
