package com.example.test.entity.vo;

import com.example.test.entity.po.Role;
import lombok.*;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleVo implements Serializable {
    private long roleId;

    private long uniqRoleId;

    private String roleName;

    private String remark;

    private Integer status;

    private String createTime;

    private String updateTime;

    private List<String> roleNameList;

    public RoleVo(Role role) {
        this.roleId = role.getId();
        this.roleName = role.getRoleName();
        this.remark = role.getRemark();
        this.status = role.getStatus();
        this.uniqRoleId = role.getRoleId();
        this.createTime = new DateTime(role.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss") ;
        this.updateTime = new DateTime(role.getUpdateTime()).toString("yyyy-MM-dd HH:mm:ss") ;
    }

}
