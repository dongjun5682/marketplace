package com.coupang.marketplace.user.controller;

import com.coupang.marketplace.aop.LoginCheck;
import com.coupang.marketplace.auth.AuthRequired;
import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.user.controller.dto.LoginRequestDTO;
import com.coupang.marketplace.user.controller.dto.SignUpRequestDTO;
import com.coupang.marketplace.user.controller.dto.UserResponseDTO;
import com.coupang.marketplace.user.domain.User;
import com.coupang.marketplace.user.service.LoginService;
import com.coupang.marketplace.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    /*
     * loginPage 이동
     * */
    @GetMapping("/login")
    public String loginPage() {
        return "login/loginPage";
    }

    @PostMapping("/login")
    public SuccessResponse login(@Valid @RequestBody LoginRequestDTO requestDto){
        loginService.login(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.OK)
                .data(loginService.getLoginUserId())
                .message("로그인 성공")
                .build();
        return res;
    }

    @PostMapping("/sign-up")
    public SuccessResponse signUp(@RequestBody SignUpRequestDTO requestDto) {
        userService.join(requestDto);

        return SuccessResponse.builder().status(StatusEnum.OK).message("회원가입성공").build();
    }

    @GetMapping("/logOut")
    @LoginCheck(type = LoginCheck.UserType.MEMBER)
    public void logoutUser(){
        loginService.logout();
    }

    @GetMapping("/my-info")
    @AuthRequired
    public UserResponseDTO myPage() {
        log.info("myPage Start");
        long loginUserId = loginService.getLoginUserId();
        User user = userService.userInfoResponse(loginUserId);

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
