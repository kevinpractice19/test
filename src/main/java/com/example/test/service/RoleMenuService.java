package com.example.test.service;



import com.example.test.entity.vo.RoleMenuVo;
import com.example.test.utils.ResultJson;
import io.swagger.models.auth.In;

import java.util.List;

public interface RoleMenuService {

        List<Integer> selectRoleMenuIdById(List<Integer> list);

        boolean deleteRoleMenuByMenuId(long menuId);

        List<Integer> selectRoleMenuByOperationBtnId(List<Integer> list);
        ResultJson<RoleMenuVo> insertOrUpdateRoleMenu(long roleId, List<String> menuNameList);

}

