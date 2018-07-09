package com.newnoa.govern.entity.vo;


import com.baozun.framework.entity.BaseVo;
import com.newnoa.govern.common.util.Constant;
import com.newnoa.govern.entity.po.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo extends BaseVo implements Serializable {

    private long userId;

    private String userName;

    private String account;

//    @JSONField(serialize = false)
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
        this.createTime = super.dateToString(user.getCreateTime(), Constant.TIME_FORMAT);
        this.updateTime = super.dateToString(user.getUpdateTime(), Constant.TIME_FORMAT);
        this.lastLoginTime = user.getLastLoginTime() == null ? null : super.dateToString(user.getLastLoginTime(), Constant.TIME_FORMAT);
    }

    public UserVo(User user, String password) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.account = user.getAccount();
        this.password = password;
        this.status = user.getStatus();
        this.userToken = user.getUserToken();
        this.lastLoginIp = user.getLastLoginIp();
        this.createTime = super.dateToString(user.getCreateTime(), Constant.TIME_FORMAT);
        this.updateTime = super.dateToString(user.getUpdateTime(), Constant.TIME_FORMAT);
        this.lastLoginTime = user.getLastLoginTime() == null ? null : super.dateToString(user.getLastLoginTime(), Constant.TIME_FORMAT);
    }


}
