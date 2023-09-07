package com.coupang.marketplace.user.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class UserResponseDTO {


    @NonNull
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String phone;
    @Builder
    public UserResponseDTO(@NonNull long id, @NonNull String name, @NonNull String email, @NonNull String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
