package com.newnoa.govern.entity.po;

import com.newnoa.govern.entity.vo.UserRoleVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
        private long id;

        private long userId;

        private long roleId;

        public UserRole(UserRoleVo vo) {
                this.id = vo.getUserRoleId();
                this.userId = vo.getUserId();
                this.roleId = vo.getRoleId();
        }

        public UserRole(Long userId,Long roleId){
                this.userId = userId;
                this.roleId = roleId;
        }
}
