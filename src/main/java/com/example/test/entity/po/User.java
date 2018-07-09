package com.newnoa.govern.entity.po;

import com.newnoa.govern.entity.vo.UserVo;
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
    private long id;

    private String userName;

    private String account;

    private String password;

    private int status;

    private String userToken;

    private String lastLoginIp;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    public User(UserVo vo) {
        this.id = vo.getUserId();
        this.userName = vo.getUserName();
        this.account = vo.getAccount();
        this.password = vo.getPassword();
        this.status = vo.getStatus();
        this.userToken = vo.getUserToken();
        this.lastLoginIp = vo.getLastLoginIp();
    }

}
