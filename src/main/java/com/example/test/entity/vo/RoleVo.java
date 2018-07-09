package com.example.test.entity.vo;

import com.example.test.entity.po.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo implements Serializable {
    private long roleId;

    private String roleName;

    private String remark;

    private int status;

    private String createTime;

    private String updateTime;

    private List<String> roleNameList;

    public RoleVo(Role role) {
        this.roleId = role.getId();
        this.roleName = role.getRoleName();
        this.remark = role.getRemark();
        this.status = role.getStatus();
    }

}
