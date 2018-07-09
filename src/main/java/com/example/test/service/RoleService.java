package com.newnoa.govern.service;

import com.baozun.framework.entity.PageInfo;
import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.entity.vo.RoleVo;

import java.util.List;

public interface RoleService {

        ResultJson<PageInfo<RoleVo>> selectRole(int pageNum, int pageSize);

        ResultJson<RoleVo> selectRoleById(long roleId);

        ResultJson<RoleVo> insertRole(RoleVo roleVo,String menuName);

        ResultJson<Boolean> deleteRoleById(long roleId);

        ResultJson updateRoleById(RoleVo roleVo);

        List<Integer> selectIdByRoleName(List<String> roleName);
}
