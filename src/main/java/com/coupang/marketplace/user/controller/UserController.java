package com.coupang.marketplace.user.controller;

import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.user.controller.dto.LoginRequestDto;
import com.coupang.marketplace.user.controller.dto.SignUpRequestDto;
import com.coupang.marketplace.user.service.LoginService;
import com.coupang.marketplace.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public SuccessResponse login(@Valid @RequestBody LoginRequestDto requestDto){
        loginService.login(requestDto);
        SuccessResponse res = SuccessResponse.builder()
                .status(StatusEnum.OK)
                .data(loginService.getLoginUserId())
                .message("로그인 성공")
                .build();
        return res;
    }

    @PostMapping("/sign-up")
    public SuccessResponse signUp(@RequestBody SignUpRequestDto requestDto) {
        userService.join(requestDto);

        return SuccessResponse.builder().status(StatusEnum.OK).message("회원가입성공").build();
    }

    @GetMapping("/logut")
    public void logoutUser(){
        loginService.logout();
    }
}
