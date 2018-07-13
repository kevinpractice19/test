package com.example.test.service.Impl;


import com.example.test.entity.po.UserRole;
import com.example.test.entity.vo.UserRoleVo;
import com.example.test.mapper.RoleMapper;
import com.example.test.mapper.UserRoleMapper;
import com.example.test.service.UserRoleService;
import com.example.test.utils.EnumsUtils;
import com.example.test.utils.ResultJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

        @Resource(name = "userRoleMapper")
        private UserRoleMapper userRoleMapper;

        @Resource(name = "roleMapper")
        private RoleMapper roleMapper;

        @Override
        public List<Long> getRoleIdListById(long userId) {
                return this.userRoleMapper.selectRoleIdById(userId);
        }

//        /**
//         * 添加用户权限或修改用户权限
//         * @param userId  用户id
//         * @param roleNameList  角色名称
//         * @return
//         */
//        @Override
//        public ResultJson<UserRoleVo> insertOrUpdateUserRole(long userId, List<String> roleNameList) {
//                List<Long> roleIdNew = roleMapper.selectIdByRoleName(roleNameList);
//                if (roleIdNew.isEmpty()) {
//                    return new ResultJson<>(EnumsUtils.FIND_FAIL);
//                }
//                List<Long> roleIdOld = userRoleMapper.selectRoleIdById(userId);
//
//                for (long roleId : roleIdOld) {
//                    if (!roleIdNew.contains(roleId))
//                        userRoleMapper.deleteUserRoleById(this.getUserRole(userId, roleId));
//                }
//                for (long roleId : roleIdNew) {
//                    if (!roleIdOld.contains(roleId)) {
//                        userRoleMapper.insertUserRole(this.getUserRole(userId, roleId));
//                    }
//                }
//                return new ResultJson<>(EnumsUtils.SUCCESS);
//        }

//        public UserRole getUserRole(long userId, long roleId) {
//                return new UserRole(userId, roleId);
//        }

}
