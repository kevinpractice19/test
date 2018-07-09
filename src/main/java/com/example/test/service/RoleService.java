package com.example.test.service;


import com.example.test.entity.vo.RoleVo;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;

import java.util.List;

public interface RoleService {

        ResultJson<PageInfo<RoleVo>> selectRole(int pageNum, int pageSize);

        ResultJson<RoleVo> selectRoleById(long roleId);

        ResultJson<RoleVo> insertRole(RoleVo roleVo, String menuName);

        ResultJson<Boolean> deleteRoleById(long roleId);

        ResultJson updateRoleById(RoleVo roleVo);

        List<Integer> selectIdByRoleName(List<String> roleName);
}
