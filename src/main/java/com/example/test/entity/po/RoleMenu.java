package com.newnoa.govern.entity.po;

import com.newnoa.govern.entity.vo.RoleMenuVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu {
        private long id;

        private long roleId;

        private long menuId;

        public RoleMenu(Long roleId , Long menuId){
                this.roleId = roleId;
                this.menuId = menuId;
        }

        public RoleMenu(RoleMenuVo vo){
                this.id = vo.getRoleMenuId();
                this.roleId =vo.getRoleId();
                this.menuId = vo.getMenuId();
        }

}
