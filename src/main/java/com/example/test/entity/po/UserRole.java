package com.example.test.entity.po;

import com.example.test.entity.vo.UserRoleVo;
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


        public UserRole(Long userId,Long roleId){
                this.userId = userId;
                this.roleId = roleId;
        }
}
