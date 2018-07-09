package com.newnoa.govern.entity.vo;

import com.newnoa.govern.common.json.ObjectUtils;
import com.newnoa.govern.entity.po.User;
import com.newnoa.govern.entity.po.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

        public UserRoleVo(HttpServletRequest request) {
                this.userRoleId = ObjectUtils.objectToLong(request.getParameter("userRoleId"));
                this.userId = ObjectUtils.objectToLong(request.getParameter("userId"));
                this.roleId = ObjectUtils.objectToLong(request.getParameter("roleId"));
        }

}