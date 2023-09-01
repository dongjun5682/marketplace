package com.coupang.marketplace.user.service;

import com.coupang.marketplace.user.controller.dto.LoginRequestDto;

public interface LoginService {

    void login(LoginRequestDto dto);

    void logout();

    long getLoginUserId();
}
