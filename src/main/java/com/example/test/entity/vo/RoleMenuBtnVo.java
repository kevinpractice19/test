package com.example.test.entity.vo;

import com.example.test.entity.po.RoleMenuBtn;
import lombok.Data;

@Data
public class RoleMenuBtnVo {

    private long roleMenuBtnId;

    private long roleId;

    private long menuBtnId;

    private int status;

    public RoleMenuBtnVo(RoleMenuBtn btn){
        this.roleMenuBtnId = btn.getId();
        this.roleId = btn.getRoleId();
        this.menuBtnId = btn.getMenuBtnId();
        this.status = btn.getStatus();

    }

}
