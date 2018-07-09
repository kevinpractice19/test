package com.newnoa.govern.entity.vo;

import com.newnoa.govern.common.json.ObjectUtils;
import com.newnoa.govern.entity.po.RoleMenu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuVo implements Serializable {
    private long roleMenuId;

    private long roleId;

    private long menuId;

    public RoleMenuVo(RoleMenu roleMenu) {
        this.roleMenuId = roleMenu.getId();
        this.roleId = roleMenu.getRoleId();
        this.menuId = roleMenu.getMenuId();
    }

    public RoleMenuVo(HttpServletRequest request) {
        this.roleMenuId = ObjectUtils.objectToLong(request.getParameter("roleMenuId"));
        this.roleId = ObjectUtils.objectToLong(request.getParameter("roleId"));
        this.menuId = ObjectUtils.objectToLong(request.getParameter("menuId"));
    }

}
