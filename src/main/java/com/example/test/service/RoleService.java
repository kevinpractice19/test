package com.example.test.service;


import com.example.test.entity.dto.RoleCreateDTO;
import com.example.test.entity.dto.RoleModifyDTO;
import com.example.test.entity.dto.RolePageDTO;
import com.example.test.entity.vo.RoleVo;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;

import java.util.List;

public interface RoleService {

        ResultJson<PageInfo<RoleVo>> selectRole(RolePageDTO pageDTO);

        ResultJson<RoleVo> selectRoleById(long roleId);

        ResultJson<RoleVo> insertRole(RoleCreateDTO createDTO);

//        ResultJson<Boolean> deleteRoleById(long roleId);

        ResultJson<RoleVo> updateRoleById(RoleModifyDTO modifyDTO);

}
