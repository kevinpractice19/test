package com.newnoa.govern.service.impl;

import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.common.util.EnumsUtils;
import com.newnoa.govern.entity.po.UserRole;
import com.newnoa.govern.entity.vo.UserRoleVo;
import com.newnoa.govern.mapper.RoleMapper;
import com.newnoa.govern.mapper.UserRoleMapper;
import com.newnoa.govern.service.UserRoleService;
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
        public List<Integer> getRoleIdListById(long userId) {
                return this.userRoleMapper.selectRoleIdById(userId);
        }

        /**
         * 添加用户权限或修改用户权限
         * @param userId  用户id
         * @param roleNameList  角色名称
         * @return
         */
        @Override
        public ResultJson<UserRoleVo> insertOrUpdateUserRole(long userId, List<String> roleNameList) {
                List<Integer> roleIdNew = roleMapper.selectIdByRoleName(roleNameList);
                if (roleIdNew.isEmpty()) {
                    return new ResultJson<>(EnumsUtils.FIND_FAIL);
                }
                List<Integer> roleIdOld = userRoleMapper.selectRoleIdById(userId);

                for (long roleId : roleIdOld) {
                    if (!roleIdNew.contains((int) roleId))
                        userRoleMapper.deleteUserRoleById(this.getUserRole(userId, roleId));
                }
                for (long roleId : roleIdNew) {
                    if (!roleIdOld.contains((int) roleId)) {
                        userRoleMapper.insertUserRole(this.getUserRole(userId, roleId));
                    }
                }
                return new ResultJson<>(EnumsUtils.SUCCESS);
        }

        public UserRole getUserRole(long userId, long roleId) {
                return new UserRole(userId, roleId);
        }

}
