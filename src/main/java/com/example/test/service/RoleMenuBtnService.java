package com.example.test.service;

import com.example.test.entity.dto.RoleMenuBtnCreateDTO;
import com.example.test.entity.vo.RoleMenuBtnVo;
import com.example.test.utils.ResultJson;

import java.util.List;

public interface RoleMenuBtnService {

    ResultJson<RoleMenuBtnVo> insertRoleMenuBtn(RoleMenuBtnCreateDTO createDTO);

//    ResultJson<RoleMenuBtnVo> selectRoleMenuBtnById(long id);

    List<Long> selectRoleMenuBtnByRoleId(List<Long> roleIdList);

}
