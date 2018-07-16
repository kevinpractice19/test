package com.example.test.service;


import com.example.test.entity.dto.PageDTO;
import com.example.test.entity.dto.RoleCreateDTO;
import com.example.test.entity.dto.RoleModifyDTO;
import com.example.test.entity.vo.RoleVo;
import com.example.test.utils.PageInfo;
import com.example.test.utils.ResultJson;

public interface RoleService {

        ResultJson<PageInfo<RoleVo>> selectRole(PageDTO pageDTO);

        ResultJson<RoleVo> selectRoleById(long roleId);

        ResultJson<RoleVo> insertRole(RoleCreateDTO createDTO);

        ResultJson<RoleVo> updateRoleById(RoleModifyDTO modifyDTO);

}
