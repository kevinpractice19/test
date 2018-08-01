package com.example.test.entity.po;

import com.example.test.entity.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String userName;

    private String account;

    private String password;

    private Integer status;

    private String userToken;

    private String lastLoginIp;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

}
