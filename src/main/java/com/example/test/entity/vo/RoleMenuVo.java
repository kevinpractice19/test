package com.example.test.entity.vo;

import com.example.test.entity.po.RoleMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuVo implements Serializable {
    private long roleMenuId;

    private long roleId;

    private long menuId;
    private long operationId;

    private int menuType;

    public RoleMenuVo(RoleMenu roleMenu) {
        this.roleMenuId = roleMenu.getId();
        this.roleId = roleMenu.getRoleId();
        this.menuId = roleMenu.getMenuId();
        this.operationId = roleMenu.getOperationId();
        this.menuType = roleMenu.getMenuType();

    }


}
