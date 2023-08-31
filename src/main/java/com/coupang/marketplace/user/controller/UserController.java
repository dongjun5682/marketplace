package com.coupang.marketplace.user.controller;

import com.coupang.marketplace.global.common.StatusEnum;
import com.coupang.marketplace.global.common.SuccessResponse;
import com.coupang.marketplace.user.controller.dto.SignUpRequestDto;
import com.coupang.marketplace.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * loginPage 이동
     * */
    @GetMapping("/login")

    public String loginPage(Model model) {
        return "login/loginPage";
    }

    @PostMapping("/sign-up")
    public SuccessResponse signUp(@RequestBody SignUpRequestDto requestDto) {
        userService.join(requestDto);

        return SuccessResponse.builder().status(StatusEnum.OK).message("회원가입성공").build();
    }
}
