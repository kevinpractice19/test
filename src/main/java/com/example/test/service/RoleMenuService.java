package com.example.test.service;



import com.example.test.entity.dto.RoleMenuCreateDTO;
import com.example.test.entity.vo.RoleMenuVo;
import com.example.test.utils.ResultJson;
import io.swagger.models.auth.In;

import java.util.List;

public interface RoleMenuService {

        List<Integer> selectRoleMenuIdById(List<Integer> list);

        boolean deleteRoleMenuByMenuId(long menuId);

        List<Integer> selectRoleMenuByMenuBtnId(List<Integer> list);
        ResultJson<RoleMenuVo> insertOrUpdateRoleMenu(RoleMenuCreateDTO createDTO);

}

