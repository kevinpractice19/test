package com.newnoa.govern.service;


import com.newnoa.govern.common.json.ResultJson;
import com.newnoa.govern.entity.vo.RoleMenuVo;

import java.util.List;

public interface RoleMenuService {

        List<Integer> selectRoleMenuIdById(List<Integer> list);

        boolean deleteRoleMenuByMenuId(long menuId);

        ResultJson<RoleMenuVo> insertOrUpdateRoleMenu(long roleId , List<String> menuNameList);

}

