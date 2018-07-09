package com.example.test.entity.vo;

import com.example.test.entity.po.User;
import com.example.test.entity.po.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleVo implements Serializable {
        private long userRoleId;

        private long userId;

        private long roleId;

        private List<RoleMenuVo> roleMenuVoList;

        private User user;

        private String userName;

        private String account;


        public UserRoleVo(UserRole userRole) {
                this.userRoleId = userRole.getId();
                this.userId = userRole.getUserId();
                this.roleId = userRole.getRoleId();
        }

}