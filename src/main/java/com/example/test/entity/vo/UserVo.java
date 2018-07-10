package com.example.test.entity.vo;


import com.example.test.entity.po.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {

    private long userId;

    private String userName;

    private String account;

    private String password;

    private int status;

    private String userToken;

    private String lastLoginIp;

    private String createTime;

    private String updateTime;

    private String lastLoginTime;


    public UserVo(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.account = user.getAccount();
        this.status = user.getStatus();
        this.userToken = user.getUserToken();
        this.lastLoginIp = user.getLastLoginIp();
        this.createTime = new DateTime(user.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss") ;
        this.updateTime = new DateTime(user.getUpdateTime()).toString("yyyy-MM-dd HH:mm:ss") ;
    }

    public UserVo(User user, String password) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.account = user.getAccount();
        this.password = password;
        this.status = user.getStatus();
        this.userToken = user.getUserToken();
        this.lastLoginIp = user.getLastLoginIp();
    }


}
