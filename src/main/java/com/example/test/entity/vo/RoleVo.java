package com.example.test.vo;

import com.example.test.po.Role;
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
public class RoleVo implements Serializable {
        private long roleId;

        private String roleName;

        private String remark;

        private int status;

        private String createTime;

        private String updateTime;

        private List<String> roleNameList;

        public RoleVo(Role role){
                this.roleId = role.getId();
                this.roleName  = role.getRoleName();
                this.remark = role.getRemark();
                this.status = role.getStatus();
//                this.createTime =super.dateToString(role.getCreateTime(), Constant.TIME_FORMAT);
//                this.updateTime = role.getUpdateTime()==null ? null : super.dateToString(role.getUpdateTime(), Constant.TIME_FORMAT);
        }

//        public RoleVo(HttpServletRequest request){
//                this.roleId = ObjectUtils.objectToLong(request.getParameter("roleId"));
//                this.roleName = ObjectUtils.objectToString(request.getParameter("roleName"));
//                this.remark = ObjectUtils.objectToString(request.getParameter("remark"));
//                this.status  =ObjectUtils.objectToInt(request.getParameter("status"));
//        }

}
