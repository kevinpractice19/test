package com.example.test.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyPasswordDTO {
    private long userId;

    private String password;

    private String newPassword;

}
