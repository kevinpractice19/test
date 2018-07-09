package com.example.test.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPageDTO {

    private int pageNum;

    private int pageSize;

    private String account;

    private String userName;
}
