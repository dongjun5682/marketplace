package com.coupang.marketplace.user.service;

import com.coupang.marketplace.user.controller.dto.LoginRequestDTO;

public interface LoginService {

    void login(LoginRequestDTO dto);

    void logout();

    long getLoginUserId();
}
