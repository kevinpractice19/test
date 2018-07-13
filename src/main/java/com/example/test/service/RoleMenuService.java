package com.example.test.service;


import com.example.test.entity.dto.RoleMenuCreateDTO;
import com.example.test.entity.vo.RoleMenuVo;
import com.example.test.utils.ResultJson;

import java.util.List;

public interface RoleMenuService {

        List<Long> selectRoleMenuIdById(List<Long> list);

        ResultJson<Boolean> deleteRoleMenuByMenuIdRoleId(long menuId, long roleId);

        List<Long> selectRoleMenuByMenuBtnId(List<Long> list);

        ResultJson<RoleMenuVo> insertRoleMenu(RoleMenuCreateDTO createDTO);

}

